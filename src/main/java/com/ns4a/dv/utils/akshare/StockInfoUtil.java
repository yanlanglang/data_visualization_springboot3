package com.ns4a.dv.utils.akshare;

import com.alibaba.fastjson2.JSON;
import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import com.ns4a.dv.utils.DateTimeUtil;
import com.ns4a.dv.utils.HttpClientUtil;
import com.ns4a.dv.vo.RealTimeStockVo;
import com.ns4a.dv.vo.StockHistVo;


import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className: StockInfoUtil
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: 股票信息工具包，包含：股票信息，及其历史数据
 **/
public class StockInfoUtil {

    /**
     * 股票基本信息的接口
     */
    private static final String API_STOCK_BASE_INFO = "http://127.0.0.1:8888/api/public/stock_individual_info_em?symbol=";

    /**
     * 股票历史信息的接口
     */
    private static final String API_STOCK_HISTORY_INFO = "http://127.0.0.1:8888/api/public/stock_zh_a_hist?";

    /**
     * 股票实时信息的接口
     */
    private static final String API_STOCK_REALTIME_STOCK_INFO = "http://127.0.0.1:8888/api/public/stock_zh_a_spot_em";

    /**
     * 线级别：日线、周线、月线
     */
    private static final String[] PERIODS = {"daily", "weekly", "monthly"};

    /**
     * 前复权、后复权、不复权
     */
    private static final String[] ADJUSTS = {"qfq", "hfq", ""};

    /**
     * 获取指定股票代码的基本信息
     * @param symbol 股票代码
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws ParseException
     */
    public static Stock getStockBaseInfo(String symbol) throws IOException, URISyntaxException, InterruptedException, ParseException {
        //获取Response实例
        HttpResponse<String> response = new HttpClientUtil().getResponse(API_STOCK_BASE_INFO+symbol);
        //获取响应数据
        List<Map<String, Object>> objects = JSON.parseArray(response.body(), (Type) Map.class);

        //除了日期之外的属性
        BigDecimal amv = (BigDecimal) objects.get(0).get("value");
        BigDecimal rmv =(BigDecimal) objects.get(1).get("value");
        String industry = (String) objects.get(2).get("value");
        String sc = (String) objects.get(4).get("value");
        String sn = (String) objects.get(5).get("value");
        BigDecimal tsi = (BigDecimal) objects.get(6).get("value");
        BigDecimal cs = (BigDecimal) objects.get(7).get("value");

        //日期需要单独处理一下(注意，当前value的格式是yyyyMMdd，且是字符串类型的伪日期)
        String startDate = String.valueOf(objects.get(3).get("value"));
        //得到想要的属性值
        Date ttm = DateTimeUtil.getDateWithStr(startDate, false);

        //获取该股票的历史数据信息
        //List<StockHist> stockHistoryInfo = getStockHistoryInfo(sc, startDate);

        //amv总市值, rmv流通市值, industry行业, ttm上市时间, sc股票代码, sn股票简称, tsi总股本, cs流通股
        return new Stock(amv, rmv, industry, ttm, sc, sn, tsi, cs);
    }


    /**
     * 获取指定股票代码的历史数据信息
     * @param stock 实例
     * @param startDate 上市时间
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public static List<StockHist> getStockHistoryInfo(Stock stock, String startDate) throws IOException, URISyntaxException, InterruptedException {
        //结束时间 ---> 当前时间
        String endDate = DateTimeUtil.getStrWithDate(new Date(), false);
        //默认日线级别、前复权
        StockHistVo shvo = new StockHistVo(stock.getSc(), PERIODS[0], startDate, endDate, ADJUSTS[0]);
        //带参数的请求字符串
        String pathParamsStr = shvo.toString();
        //真正的请求路径
        String path = API_STOCK_HISTORY_INFO + pathParamsStr;

        //获取响应消息
        HttpResponse<String> response = new HttpClientUtil().getResponse(path);

        //为每一条历史信息设置stock
        List<StockHist> stockHists = JSON.parseArray(response.body(), StockHist.class);
        //打印长度
        System.out.println("MyInfo---历史数据的长度为--->"+stockHists.size());
        for (StockHist stockHist : stockHists) {
            stockHist.setStock(stock);
        }

        //返回List<StockHist>
        return stockHists;
    }

    /**
     * 获取A股 所有股票 实时信息的集合
     * @return
     */
    public static List<RealTimeStockVo> getRealTimeStocks() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = new HttpClientUtil().getResponse(API_STOCK_REALTIME_STOCK_INFO);
        return JSON.parseArray(response.body(), RealTimeStockVo.class);
    }


    /**
     * 获取指定股票代码的单支股票实时信息
     * @param sc 股票代码
     * @return
     */
    public static RealTimeStockVo getRealTimeStock(String sc) throws Exception {
        List<RealTimeStockVo> realTimeStockVos = getRealTimeStocks();
        for (RealTimeStockVo realTimeStockVo : realTimeStockVos) {
            if (realTimeStockVo.getSc().equals(sc)){
                return realTimeStockVo;
            }
        }
        throw new Exception("{StockInfoUtil.getRealTimeStock}发生错误了！");
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ParseException {
        String code = "002585";

        Stock baseStockInfo = getStockBaseInfo(code);
        System.out.println(baseStockInfo);

        //不带短横线的上市时间
        String startDate = DateTimeUtil.getStrWithDate(baseStockInfo.getTtm(), false);
        List<StockHist> stockHistoryInfo = getStockHistoryInfo(baseStockInfo, startDate);

        String s = JSON.toJSONString(stockHistoryInfo);
        System.out.println(s);

    }


}
