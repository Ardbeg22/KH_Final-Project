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
public class CommunityReply {

    private int comRplNo;
    private String comNo;
    private String userNo;
    private String comRplContent;
    private Date comRplRegisterDate;
    private String comRplStatus;
    
    private String userNickname;
    private String reportReason;
    private Date reportDate;
    private String reportStatus;
    private int reportNo;
    private String userId;
}