package com.example.oplus.`interface`

import com.example.oplus.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InventoryInterface {
    @POST("api/tonkho/GetSoLuongTonKho")
    fun getSoLuongTonKHo() : Call<BaseResponse<ResultStatusInventory>>
    @POST("api/tonkho/GetDanhSachTonKho")
    fun getDanhSachTonKho(@Body danhSachTonKho:DanhSachTonKhoRequestDTO): Call<BaseResponse<ResultItemInventory>>
    @POST("api/tonkho/LayThuocTinhDong")
    fun getThuocTinhDong(@Body thuocTinhDong: ThuocTinhDongRequestDTO): Call<BaseResponse<ResultThuocTinhDong>>
}