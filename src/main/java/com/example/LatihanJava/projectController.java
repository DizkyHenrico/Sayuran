package com.example.LatihanJava;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@Controller
public class projectController {
    
    @RequestMapping("/input")
    public String getData(HttpServletRequest data, Model discountprocess){
       
        String inputname = data.getParameter("nama_barang");
        
        String inputprice = data.getParameter("harga_barang");
        
        String inputquantity = data.getParameter("jumlah_beli");
        
        String inputUang = data.getParameter("uang");
        
        String diskon = "";
        Double Tdisc = null;
        
        Double iUang = Double.valueOf(inputUang);
        Double iPrice = Double.valueOf(inputprice);
        Double iTotal = Double.valueOf(inputquantity);
        Double PriceTotal = iPrice * iTotal;
        Double getTotal = null;
        
        if (PriceTotal < 16000)
        {
            getTotal = PriceTotal - (0 * PriceTotal/100);
            diskon = "0%";
            Tdisc = 0 * PriceTotal/100;
        }
        else if (PriceTotal >= 16000 && PriceTotal < 25000)
        {
            getTotal = PriceTotal - (10 * PriceTotal/100);
            diskon = "10%";
            Tdisc = 10 * PriceTotal/100;
        }
        else if (PriceTotal >= 25000)
        {
            getTotal = PriceTotal - (15 * PriceTotal/100);
            diskon = "15%";
            Tdisc = 15 * PriceTotal/100;
        }
        
        String Ket = "";
        Double kembalian = iUang - getTotal;
        Double kekurangan = getTotal - iUang;
        
        if (getTotal < iUang)
        {
            Ket = "Kembalian anda Rp. " + kembalian;
        }
        else if (getTotal > iUang)
        {
            Ket = "Uang anda kurang Rp." + kekurangan;
        }
        else 
        {
            Ket = "Uang anda pas";
        }
                
        discountprocess.addAttribute("name", inputname);
        discountprocess.addAttribute("price", inputprice);
        discountprocess.addAttribute("quantity", inputquantity);
        discountprocess.addAttribute("total", getTotal);
        discountprocess.addAttribute("dc", diskon);
        discountprocess.addAttribute("duit", inputUang);
        discountprocess.addAttribute("Ket", Ket);
        discountprocess.addAttribute("HargaAwal", PriceTotal);
        discountprocess.addAttribute("HargaDiskon", Tdisc);
        
        return "View";
    }    
}