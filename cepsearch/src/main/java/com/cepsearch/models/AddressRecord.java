package com.cepsearch.models;

import com.google.gson.annotations.SerializedName;

public record AddressRecord(
                @SerializedName("cep") String cep,
                @SerializedName("logradouro") String logradouro,
                @SerializedName("complemento") String complemento,
                @SerializedName("bairro") String bairro,
                @SerializedName("localidade") String localidade,
                @SerializedName("uf") String uf,
                @SerializedName("ibge") String ibge,
                @SerializedName("gia") String gia,
                @SerializedName("ddd") String ddd,
                @SerializedName("siafi") String siafi) {

}