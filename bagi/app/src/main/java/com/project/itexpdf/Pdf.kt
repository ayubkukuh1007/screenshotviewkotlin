package com.project.itexpdf

import android.content.Context
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.itextpdf.text.pdf.draw.VerticalPositionMark
import java.io.File
import java.io.FileOutputStream
import java.text.NumberFormat
import java.util.*

class Pdf (private val dataUmroh: ModelUmroh, private val mContext: Context) {

    private val localeID : Locale
    private val formatRupiah : NumberFormat

    init {
        localeID = Locale("in", "ID")
        formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    }

    fun savePDF() {

        var now         = Date()
        val filename    = "PesertaUmroh.pdf"
        val path        = File(mContext.getExternalFilesDir(null)!!.absolutePath + "/Umroh")

        if (!path.exists())path.mkdirs()

        var file: File = File(path, filename)

        if (file.exists())
        {
            file.delete()
            file = File(path, filename)
        }

        val document = Document()

        PdfWriter.getInstance(document, FileOutputStream(file))

        document.open()
        document.pageSize = PageSize.A4
        document.addCreationDate()
        document.addAuthor("")
        document.addCreator("")

        val mColorAccent        = BaseColor(0, 153, 204, 255)
        val mHeadingFontSize    = 20.0f
        val mValueFontSize      = 26.0f

        val basecheckbox    = BaseFont.createFont("res/font/checkbox.ttf", BaseFont.IDENTITY_H, false)
        val baseuncheckbox  = BaseFont.createFont("res/font/uncheckbox.ttf", BaseFont.IDENTITY_H, false)

        val checkbox    = Font(basecheckbox, 30f, Font.BOLD)
        val uncheckbox  = Font(baseuncheckbox, 30f, Font.NORMAL)

        val fontMontserrat =  BaseFont.createFont("res/font/fontmontserrat.ttf", "UTF-8", BaseFont.EMBEDDED)
        val lineSeparator  =  LineSeparator()
                              lineSeparator.lineColor = BaseColor(0, 0, 0, 68)

        //sesuaikan size label
        val fontmontserratForHeader = Font(
            fontMontserrat,
            36.0f, Font.NORMAL,
            BaseColor.BLACK)

        val fontnormalcolorBlack = Font(
            fontMontserrat,
            mValueFontSize,
            Font.NORMAL,
            BaseColor.BLACK
        )
        val fontnormalcolorBlue = Font(
            fontMontserrat,
            mValueFontSize,
            Font.NORMAL,
            mColorAccent
        )
        val fontheading = Font(
            fontMontserrat,
            mHeadingFontSize,
            Font.NORMAL,
            BaseColor.BLACK
        )

        //Nama Paket
        val paragraf1 = Paragraph(Chunk(dataUmroh.Namapaket,fontmontserratForHeader))
                        paragraf1.alignment = Element.ALIGN_LEFT
                        document.add(paragraf1)

        //no pesanan
        val paragraf2        = Paragraph(Chunk("No.pesanan " + dataUmroh.Nopesan, fontnormalcolorBlue))
                               paragraf2.alignment = Element.ALIGN_LEFT
                               document.add(paragraf2)

        //seprator 1
        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //Status Data Peserta
        val  paragraf3          =   Paragraph("Data peserta", fontnormalcolorBlack)
                                    paragraf3.add(Chunk(VerticalPositionMark()))
                                    paragraf3.add(dataUmroh.StatusDataPeserta)
                                    document.add(paragraf3)

        //seprator 2
        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //Namaperserta
        val paragraf4  = Paragraph("Nama Peserta", fontnormalcolorBlack)
                         paragraf4.add(Chunk(VerticalPositionMark()))
                         paragraf4.add(dataUmroh.NamaPeserta)
                         document.add(paragraf4)
        //Nohp
        val paragraf5  = Paragraph("No.Hp", fontnormalcolorBlack)
                         paragraf5.add(Chunk(VerticalPositionMark()))
                         paragraf5.add(dataUmroh.Nohp)
                         document.add(paragraf5)
        //noktp
        val paragraf6 = Paragraph("No.Ktp", fontnormalcolorBlack)
                        paragraf6.add(Chunk(VerticalPositionMark()))
                        paragraf6.add(dataUmroh.Noktp)
                        document.add(paragraf6)

        //nopasspor
        val paragraf7 = Paragraph("No.Pasport", fontnormalcolorBlack)
                        paragraf7.add(Chunk(VerticalPositionMark()))
                        paragraf7.add(dataUmroh.NoPasort)
                        document.add(paragraf7)

        //status dokumen
        val paragraf8 = Paragraph("Status Dokumen", fontnormalcolorBlack)
                        paragraf8.add(Chunk(VerticalPositionMark()))
                        paragraf8.add(dataUmroh.StatusDokument)
                        document.add(paragraf8)

        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //R checked
        //o Uncheked

        val table = PdfPTable(4)

        table.horizontalAlignment = Element.ALIGN_LEFT
        val cell1 = PdfPCell(
            Paragraph(
                if (dataUmroh.Ktp) "R" else "o",
                if (dataUmroh.Ktp) checkbox else uncheckbox
            )
        )
        val cell2 = PdfPCell(Paragraph("KTP", fontnormalcolorBlack))
        val cell3 = PdfPCell(
            Paragraph(
                if (dataUmroh.Foto) "R" else "o",
                if (dataUmroh.Foto) checkbox else uncheckbox
            )
        )
        val cell4 = PdfPCell(Paragraph("FOTO", fontnormalcolorBlack))
        val cell5 = PdfPCell(
            Paragraph(
                if (dataUmroh.Pasport) "R" else "o",
                if (dataUmroh.Pasport) checkbox else uncheckbox
            )
        )
        val cell6 = PdfPCell(Paragraph("PASPORT", fontnormalcolorBlack))
        val cell7 = PdfPCell(
            Paragraph(
                if (dataUmroh.KartuKeluarga) "R" else "o",
                if (dataUmroh.KartuKeluarga) checkbox else uncheckbox
            )
        )
        val cell8 = PdfPCell(Paragraph("KK", fontnormalcolorBlack))
        val cell9 = PdfPCell(
            Paragraph(
                if (dataUmroh.BukuNikah) "R" else "o",
                if (dataUmroh.BukuNikah) checkbox else uncheckbox
            )
        )
        val cell10 = PdfPCell(Paragraph("BUKU NIKAH", fontnormalcolorBlack))
        val cell11 = PdfPCell(
            Paragraph(
                if (dataUmroh.BukuNikah) "R" else "o",
                if (dataUmroh.KartuKuning) checkbox else uncheckbox
            )
        )
        val cell12 = PdfPCell(Paragraph("KARTU KUNING", fontnormalcolorBlack))
        val cell13 = PdfPCell(
            Paragraph(
                if (dataUmroh.Kitas) "R" else "o",
                if (dataUmroh.Kitas) checkbox else uncheckbox
            )
        )
        val cell14 = PdfPCell(Paragraph("KITAS", fontnormalcolorBlack))
        val cell15 = PdfPCell(Paragraph("", fontnormalcolorBlack))
        val cell16 = PdfPCell(Paragraph("", fontnormalcolorBlack))

        cell1.border = Rectangle.NO_BORDER
        cell2.border = Rectangle.NO_BORDER
        cell3.border = Rectangle.NO_BORDER
        cell4.border = Rectangle.NO_BORDER
        cell5.border = Rectangle.NO_BORDER
        cell6.border = Rectangle.NO_BORDER
        cell7.border = Rectangle.NO_BORDER
        cell8.border = Rectangle.NO_BORDER
        cell9.border = Rectangle.NO_BORDER
        cell10.border = Rectangle.NO_BORDER
        cell11.border = Rectangle.NO_BORDER
        cell12.border = Rectangle.NO_BORDER
        cell13.border = Rectangle.NO_BORDER
        cell14.border = Rectangle.NO_BORDER
        cell15.border = Rectangle.NO_BORDER
        cell16.border = Rectangle.NO_BORDER
        cell3.border = Rectangle.NO_BORDER

        cell1.horizontalAlignment = Element.ALIGN_CENTER
        cell2.horizontalAlignment = Element.ALIGN_LEFT
        cell3.horizontalAlignment = Element.ALIGN_CENTER
        cell4.horizontalAlignment = Element.ALIGN_LEFT
        cell5.horizontalAlignment = Element.ALIGN_CENTER
        cell6.horizontalAlignment = Element.ALIGN_LEFT
        cell7.horizontalAlignment = Element.ALIGN_CENTER
        cell8.horizontalAlignment = Element.ALIGN_LEFT
        cell9.horizontalAlignment = Element.ALIGN_CENTER
        cell10.horizontalAlignment = Element.ALIGN_LEFT
        cell11.horizontalAlignment = Element.ALIGN_CENTER
        cell12.horizontalAlignment = Element.ALIGN_LEFT
        cell13.horizontalAlignment = Element.ALIGN_CENTER
        cell14.horizontalAlignment = Element.ALIGN_LEFT
        cell15.horizontalAlignment = Element.ALIGN_CENTER
        cell16.horizontalAlignment = Element.ALIGN_LEFT

        table.addCell(cell1)
        table.addCell(cell2)
        table.addCell(cell3)
        table.addCell(cell4)
        table.addCell(cell5)
        table.addCell(cell6)
        table.addCell(cell7)
        table.addCell(cell8)
        table.addCell(cell9)
        table.addCell(cell10)
        table.addCell(cell11)
        table.addCell(cell12)
        table.addCell(cell13)
        table.addCell(cell14)
        table.addCell(cell15)
        table.addCell(cell16)

        table.widthPercentage   = 100f
        val columnWidths        = floatArrayOf(10f, 50f, 10f, 50f)
        table.setWidths(columnWidths)

        document.add(table)

        //seprator
        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //status pembayaran
        val paragraf9 = Paragraph("Status Pembayaran", fontnormalcolorBlack)
                        paragraf9.add(Chunk(VerticalPositionMark()))
                        paragraf9.add(dataUmroh.StatusPembayaran)
                        document.add(paragraf9)

        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //tiepkamar
        val paragraf10 =    Paragraph("Kamar " + dataUmroh.Room, fontnormalcolorBlack)
                            paragraf10.add(Chunk(VerticalPositionMark()))
                            paragraf10.add("Total: " + formatRupiah.format(dataUmroh.Total))
                            document.add(paragraf10)

        //sisa
        val paragraf11 =    Paragraph("", fontnormalcolorBlack)
                            paragraf11.add(Chunk(VerticalPositionMark()))
                            paragraf11.add("Sisa : " + formatRupiah.format(dataUmroh.Sisa))
                            document.add(paragraf11)

        //seprator
        document.add(Paragraph(""))
        document.add(Chunk(lineSeparator))
        document.add(Paragraph(""))

        //pay-tanggal
        val paragraf12 =    Paragraph("Tanggal Bayar", fontnormalcolorBlack)
                            paragraf12.add(Chunk(VerticalPositionMark()))
                            paragraf12.add(dataUmroh.Tanggal)
                            document.add(paragraf12)

        //totalbayar
        val paragraf13 =    Paragraph("Total bayar", fontnormalcolorBlack)
                            paragraf13.add(Chunk(VerticalPositionMark()))
                            paragraf13.add(formatRupiah.format(dataUmroh.Total - dataUmroh.Sisa))
                            document.add(paragraf13)

        //jenis pembayaran
        val paragraf14 =    Paragraph("Tunai", fontnormalcolorBlack)
                            paragraf14.add(Chunk(VerticalPositionMark()))
                            paragraf14.add("")
                            document.add(paragraf14)

        //penerima
        val paragraf15 =    Paragraph("Nama Penerima", fontnormalcolorBlack)
                            paragraf15.add(Chunk(VerticalPositionMark()))
                            paragraf15.add("")
                            document.add(paragraf15)

        document.close()

    }

}

