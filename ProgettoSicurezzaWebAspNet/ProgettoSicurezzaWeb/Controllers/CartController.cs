using ProgettoSicurezzaWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ProgettoSicurezzaWeb.Controllers
{
    public class CartController : ApiController
    {
        [CustomAutorized]
        /// <summary>
        /// api/cart
        /// </summary>
        /// <returns>lista prodotti nel carrello</returns>
        public IEnumerable<ProductCart> Get()
        {
            List<ProductCart> cart = new List<ProductCart>();

            cart.Add(new ProductCart
            {
                Id = 1,
                Id_Prodotto = 1,
                Qta = 10
            });

            cart.Add(new ProductCart
            {
                Id = 2,
                Id_Prodotto = 2,
                Qta = 20
            });

            cart.Add(new ProductCart
            {
                Id = 3,
                Id_Prodotto = 3,
                Qta = 30
            });

            cart.Add(new ProductCart
            {
                Id = 4,
                Id_Prodotto = 4,
                Qta = 40
            });

            cart.Add(new ProductCart
            {
                Id = 5,
                Id_Prodotto = 5,
                Qta = 50
            });
            return cart;
        }

        public int Get(int id_Product)
        {
            return id_Product;

        }


    }
}
