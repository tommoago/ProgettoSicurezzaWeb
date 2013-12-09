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
        DataAccess data;

        public CartController()
        {
            data = new DataAccess();
        }
        
        /// <summary>
        /// api/cart
        /// </summary>
        /// <returns>lista prodotti nel carrello</returns>
        [CustomAutorized]
        public IEnumerable<ProductCart> Get()
        {
            return data.GetCart();
        }
        [CustomAutorized]
        public int Get(int id_Product)
        {
            return id_Product;

        }

        [CustomAutorized]
        public void Post([FromBody] ProductCart p)
        {
            data.InsertProduct(p);
        }


        [CustomAutorized]
        public void Delete([FromBody] ProductCart p)
        {
            data.DeleteByCart(p);
        }

        public string Options()
        {
            return "";
        }


    }
}
