using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProgettoSicurezzaWeb.Models
{
    public class Cart
    {
        public List<ProductCart> Prods;

        public Cart()
        {
            Prods=new List<ProductCart>();
        }

        public void Insert(Product p, int qta)
        {
            Prods.Add(new ProductCart
            {
                Id_Prodotto = p.Id,
                Qta = qta
            });
        }

        public bool DeleteByProd(int id_Product)
        {
            return Prods.Remove(Prods.FirstOrDefault(p => p.Id_Prodotto.Equals(id_Product)));
        }

        public bool DeleteById(int id)
        {
            return Prods.Remove(Prods.FirstOrDefault(p => p.Id.Equals(id)));
        }

    }
}