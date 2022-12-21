package com.urfavoriteott.ufo.community.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Community {

    private int comNo;
    private String movieId;
    private String tvId;
    private String userNo;
    private String comTitle;
    private String comContent;
    private Date comRegisterDate;
    private int comHit;
    private String comStatus;
    
    private String userNickname;
    private String reportReason;
    private Date reportDate;
    private String reportStatus;
    private int reportNo;
    private String userId;
}