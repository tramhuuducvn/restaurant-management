package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem  {
    private int itemId;
    private String name;
    private String description;
    private String image;
    private double price;
    private List<String> additionalDetail;
}