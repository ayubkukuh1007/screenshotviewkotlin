package com.project.itexpdf



data class ModelUmroh (var Namapaket : String,
                       var Nopesan : String,

                       var StatusDataPeserta : String,
                       var NamaPeserta : String,
                       var Nohp : String,
                       var Noktp : String,
                       var NoPasort : String,

                       var StatusDokument : String, //Cek kelengkapan Dokument Peserta
                       var Ktp : Boolean,
                       var Pasport : Boolean,
                       var BukuNikah : Boolean,
                       var Foto : Boolean,
                       var KartuKeluarga : Boolean,
                       var KartuKuning : Boolean,
                       var Kitas : Boolean,

                       var Room : String,
                       var StatusPembayaran : String, // Cek status Pembayaran
                       var Tanggal : String,
                       var Total : Double,
                       var Sisa : Double,
                       var TotalBayar : Double  )