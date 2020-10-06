package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InventoryInterface {
    @POST("api/tonkho/GetSoLuongTonKho")
    fun getSoLuongTonKHo() : Call<BaseResponse<ResultStatusInventory>>
    @POST("api/tonkho/GetDanhSachTonKho")
    fun getDanhSachTonKho(@Body danhSachTonKho: DanhSachTonKhoRequestDTO): Call<BaseResponse<BaseResultItem<FarmDevice>>>
    @POST("api/tonkho/LayThuocTinhDong")
    fun getThuocTinhDong(@Body thuocTinhDong: ThuocTinhDongRequestDTO): Call<BaseResponse<ResultThuocTinhDong>>
    @POST("api/tonkho/DemLichMuaHang")
    fun demLichMuaHang(@Body xacNhan: XacNhanRequestDTO): Call<BaseResponse<BaseResultItem<StatusConfirmInventory>>>
    @POST("api/tonkho/lichmuahangtheongay")
    fun lichMuaHangTheoNgay(@Body request: DetailConfirmRequestDTO): Call<BaseResponse<BaseResultItem<ItemConfirmInventory>>>
    @POST("api/tonkho/ChiTietLichMuaHang")
    fun chiTietMuaHang(@Body rq: XacNhanRequestDTO): Call<BaseResponse<ResultDetailBuy>>
    @POST("api/tonkho/SearchItem")
    fun searchItem(@Body rq:SearchRequestDTO): Call<BaseResponse<BaseResultItem<FarmDevice>>>
}