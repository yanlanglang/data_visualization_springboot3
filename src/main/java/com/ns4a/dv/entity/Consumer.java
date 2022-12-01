package com.ns4a.dv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @className: Consumer
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 客户，含基本信息和两个篮子(自选篮 和 购买篮)
 **/
@Data
@Entity
@Table(name = "t_consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //登录用
    private String username;

    @JsonIgnore
    private String password;

    private String avatar;

    private String gender;

    private String nickname;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date createDate;

    /**
     * 一个用户，有多只购买股
     */
    @OneToMany(mappedBy = "consumer")
    private List<PurchasedStock> purchasedStocks;

    /**
     * 一个用户，有多只自选股
     */
    @OneToMany(mappedBy = "consumer")
    private List<OptionalStock> optionalStocks;

    //默认为true，注销为false
    private boolean hasRoot;

    public Consumer() {
    }

    public Consumer(String username, String password, String avatar, String gender, String nickname, Date createDate, List<PurchasedStock> purchasedStocks, List<OptionalStock> optionalStocks, boolean hasRoot) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.nickname = nickname;
        this.createDate = createDate;
        this.purchasedStocks = purchasedStocks;
        this.optionalStocks = optionalStocks;
        this.hasRoot = hasRoot;
    }
}
