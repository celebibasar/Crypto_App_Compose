package com.basarcelebi.cryptoapp.repositories

import com.basarcelebi.cryptoapp.models.ApiBaseModel
import com.basarcelebi.cryptoapp.models.BaseModel
import com.basarcelebi.cryptoapp.models.CryptoCurrencyList

interface CryptoRepo {
    suspend fun getListing():BaseModel<ApiBaseModel<CryptoCurrencyList>>


}