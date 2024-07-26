package com.example.gjlunchbox.Onboarding

import androidx.compose.foundation.pager.PageSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IndicatorUI(pageSize: Int, currentPage: Int){

}
@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview1(){
    IndicatorUI(pageSize = 3, currentPage = 0)
}
@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview2(){
    IndicatorUI(pageSize = 3, currentPage = 1)
}
@Preview(showBackground = true)
@Composable
fun IndicatorUIPreview3(){
    IndicatorUI(pageSize = 3, currentPage = 2)
}
