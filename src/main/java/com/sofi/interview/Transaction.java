package com.sofi.interview;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @CsvBindByPosition(position = 0)
    private int userId;

    @CsvBindByPosition(position = 1)
    private String merchantName;

    @CsvBindByPosition(position = 2)
    private int merchantId;

    @CsvBindByPosition(position = 3)
    private double price;

    @CsvBindByPosition(position = 4)
    private String purchaseDate;

    @Id
    @CsvBindByPosition(position = 5)
    private int id;

    @Override
    public String toString() {
        return "Transaction{" +
                "userId=" + userId +
                ", merchantName='" + merchantName + '\'' +
                ", merchantId=" + merchantId +
                ", price=" + price +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", id=" + id +
                '}';
    }



}
