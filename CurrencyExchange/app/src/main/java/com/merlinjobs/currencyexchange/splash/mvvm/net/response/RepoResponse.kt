package com.merlinjobs.currencyexchange.splash.mvvm.net.response

data class RepoResponse(val base: String,
                        val date: String,
                        val rates: HashMap<String, Double>)