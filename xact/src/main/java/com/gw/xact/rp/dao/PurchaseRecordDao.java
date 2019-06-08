package com.gw.xact.rp.dao;

import org.springframework.stereotype.Repository;

import com.gw.xact.rp.pojo.PurchaseRecordPo;

/**** imports ****/
@Repository
public interface PurchaseRecordDao {
    public int insertPurchaseRecord(PurchaseRecordPo pr);
}