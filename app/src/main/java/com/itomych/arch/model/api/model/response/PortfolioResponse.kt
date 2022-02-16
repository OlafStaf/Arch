package com.itomych.arch.model.api.model.response

data class PortfolioResponse(val items: List<PortfolioItem>)

data class PortfolioItem(val name: String, val balance: Long)
