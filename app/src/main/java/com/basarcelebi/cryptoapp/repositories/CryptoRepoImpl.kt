package com.basarcelebi.cryptoapp.repositories

import com.basarcelebi.cryptoapp.models.ApiBaseModel
import com.basarcelebi.cryptoapp.models.BaseModel
import com.basarcelebi.cryptoapp.models.CryptoCurrencyList
import com.basarcelebi.cryptoapp.network.Api

class CryptoRepoImpl(private val api:Api):CryptoRepo {
    override suspend fun getListing():BaseModel<ApiBaseModel<CryptoCurrencyList>>{
        try {
            api.getListing(
                start = 1,
                limit = 100
            ).also {
                if(it.isSuccessful){
                    return BaseModel.Success(data = it.body()!!)

                }else{
                    return BaseModel.Error("We have an error!")

                }
            }
        }
        catch (e:Exception){
            return BaseModel.Error(e.message.toString())

        }
    }
}