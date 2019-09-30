package com.seoulcontest.firstcitizen.network.vo


import com.google.gson.annotations.SerializedName

data class NaverReverseGCResponse(
    @SerializedName("status")
    val status: Status,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Status(
        @SerializedName("code")
        val code: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("message")
        val message: String
    )

    data class Result(
        @SerializedName("name")
        val name: String,
        @SerializedName("code")
        val code: Code,
        @SerializedName("region")
        val region: Region,
        @SerializedName("land")
        val land: Land
    ) {
        data class Code(
            @SerializedName("id")
            val id: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("mappingId")
            val mappingId: String
        )

        data class Land(
            @SerializedName("type")
            val type: String,
            @SerializedName("number1")
            val number1: String,
            @SerializedName("number2")
            val number2: String,
            @SerializedName("addition0")
            val addition0: Addition0,
            @SerializedName("addition1")
            val addition1: Addition1,
            @SerializedName("addition2")
            val addition2: Addition2,
            @SerializedName("addition3")
            val addition3: Addition3,
            @SerializedName("addition4")
            val addition4: Addition4,
            @SerializedName("name")
            val name: String,
            @SerializedName("coords")
            val coords: Coords
        ) {
            data class Coords(
                @SerializedName("center")
                val center: Center
            ) {
                data class Center(
                    @SerializedName("crs")
                    val crs: String,
                    @SerializedName("x")
                    val x: Int,
                    @SerializedName("y")
                    val y: Int
                )
            }

            data class Addition2(
                @SerializedName("type")
                val type: String,
                @SerializedName("value")
                val value: String
            )

            data class Addition4(
                @SerializedName("type")
                val type: String,
                @SerializedName("value")
                val value: String
            )

            data class Addition0(
                @SerializedName("type")
                val type: String,
                @SerializedName("value")
                val value: String
            )

            data class Addition1(
                @SerializedName("type")
                val type: String,
                @SerializedName("value")
                val value: String
            )

            data class Addition3(
                @SerializedName("type")
                val type: String,
                @SerializedName("value")
                val value: String
            )
        }

        data class Region(
            @SerializedName("area0")
            val area0: Area0,
            @SerializedName("area1")
            val area1: Area1,
            @SerializedName("area2")
            val area2: Area2,
            @SerializedName("area3")
            val area3: Area3,
            @SerializedName("area4")
            val area4: Area4
        ) {
            data class Area2(
                @SerializedName("name")
                val name: String,
                @SerializedName("coords")
                val coords: Coords
            ) {
                data class Coords(
                    @SerializedName("center")
                    val center: Center
                ) {
                    data class Center(
                        @SerializedName("crs")
                        val crs: String,
                        @SerializedName("x")
                        val x: Double,
                        @SerializedName("y")
                        val y: Double
                    )
                }
            }

            data class Area3(
                @SerializedName("name")
                val name: String,
                @SerializedName("coords")
                val coords: Coords
            ) {
                data class Coords(
                    @SerializedName("center")
                    val center: Center
                ) {
                    data class Center(
                        @SerializedName("crs")
                        val crs: String,
                        @SerializedName("x")
                        val x: Double,
                        @SerializedName("y")
                        val y: Double
                    )
                }
            }

            data class Area4(
                @SerializedName("name")
                val name: String,
                @SerializedName("coords")
                val coords: Coords
            ) {
                data class Coords(
                    @SerializedName("center")
                    val center: Center
                ) {
                    data class Center(
                        @SerializedName("crs")
                        val crs: String,
                        @SerializedName("x")
                        val x: Int,
                        @SerializedName("y")
                        val y: Int
                    )
                }
            }

            data class Area1(
                @SerializedName("name")
                val name: String,
                @SerializedName("coords")
                val coords: Coords,
                @SerializedName("alias")
                val alias: String
            ) {
                data class Coords(
                    @SerializedName("center")
                    val center: Center
                ) {
                    data class Center(
                        @SerializedName("crs")
                        val crs: String,
                        @SerializedName("x")
                        val x: Double,
                        @SerializedName("y")
                        val y: Double
                    )
                }
            }

            data class Area0(
                @SerializedName("name")
                val name: String,
                @SerializedName("coords")
                val coords: Coords
            ) {
                data class Coords(
                    @SerializedName("center")
                    val center: Center
                ) {
                    data class Center(
                        @SerializedName("crs")
                        val crs: String,
                        @SerializedName("x")
                        val x: Int,
                        @SerializedName("y")
                        val y: Int
                    )
                }
            }
        }
    }
}