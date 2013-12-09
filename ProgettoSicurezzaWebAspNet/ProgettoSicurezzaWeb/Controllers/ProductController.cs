using ProgettoSicurezzaWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ProgettoSicurezzaWeb.Controllers
{

    public class ProductController : ApiController
    {
        DataAccess data;


        public ProductController()
        {
            data = new DataAccess();
        }
        /// <summary>
        /// api/product
        /// </summary>
        /// <returns>lista prodotti</returns>
        [CustomAutorized]
        public IEnumerable<Product> Get()
        {

            return data.GetAllProducts();
        }

        /// <summary>
        /// api/product?id
        /// </summary>
        /// <param name="id">id del prodotto</param>
        /// <returns>prodotto</returns>
        [CustomAutorized]
        public Product Get(int id)
        {
            return data.GetProductById(id);

        }

        public string Options()
        {
            return "";
        }


    }
}
