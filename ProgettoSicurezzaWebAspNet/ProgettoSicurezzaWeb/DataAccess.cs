using ProgettoSicurezzaWeb.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace ProgettoSicurezzaWeb
{
    public class DataAccess
    {
        private string connectionString;

        public DataAccess()
        {
            this.connectionString =
                ConfigurationManager.ConnectionStrings["cs"].ConnectionString;
        }

        public IEnumerable<Product> GetAllProducts()
        {
            List<Product> result = new List<Product>();

            string query = @"
SELECT [Id]
      ,[Nome]
      ,[Descrizione]
  FROM [dbo].[Product]";

            using (SqlConnection connection =
                            new SqlConnection(this.connectionString))
            {
                connection.Open();

                using (SqlCommand command =
                            new SqlCommand(query, connection))
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Product product = new Product();
                            product.Id = reader.GetInt32(reader.GetOrdinal("Id"));
                            product.Nome = reader.GetString(reader.GetOrdinal("Nome"));
                            product.Descrizione = reader.GetString(reader.GetOrdinal("Descrizione"));
                            result.Add(product);
                        }

                    }
                }
            }


            return result;
        }


        public Product GetProductById(int id)
        {
            string query = @"
SELECT [Id]
      ,[Nome]
      ,[Descrizione]
  FROM [dbo].[Product]
WHERE Id = @id";

            using (SqlConnection connection =
                            new SqlConnection(this.connectionString))
            {
                connection.Open();

                using (SqlCommand command =
                            new SqlCommand(query, connection))
                {
                    command.Parameters.Add(
                        new SqlParameter("id", id));

                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            Product product = new Product();
                            product.Id = reader.GetInt32(reader.GetOrdinal("Id"));
                            product.Nome = reader.GetString(reader.GetOrdinal("Nome"));
                            product.Descrizione = reader.GetString(reader.GetOrdinal("Descrizione"));

                            return product;
                        }

                    }
                }
            }
            return null;
        }




        public IEnumerable<ProductCart> GetCart()
        {
            List<ProductCart> result = new List<ProductCart>();

            string query = @"
SELECT [Id]
      ,[Id_prodotto]
      ,[Qta]
  FROM [dbo].[Cart]";

            using (SqlConnection connection =
                            new SqlConnection(this.connectionString))
            {
                connection.Open();

                using (SqlCommand command =
                            new SqlCommand(query, connection))
                {
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            ProductCart product = new ProductCart();
                            product.Id = reader.GetInt32(reader.GetOrdinal("Id"));
                            product.Id_Prodotto = reader.GetInt32(reader.GetOrdinal("Id_prodotto"));
                            product.Qta = reader.GetInt32(reader.GetOrdinal("Qta"));
                            result.Add(product);
                        }

                    }
                }
            }


            return result;
        }



        public ProductCart GetCartProduct(int id)
        {
            string query = @"
SELECT [Id]
      ,[Id_prodotto]
      ,[Qta]
  FROM [dbo].[Cart]
WHERE Id_prodotto = @id";

            using (SqlConnection connection =
                            new SqlConnection(this.connectionString))
            {
                connection.Open();

                using (SqlCommand command =
                            new SqlCommand(query, connection))
                {
                    command.Parameters.Add(
                        new SqlParameter("id", id));

                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            ProductCart product = new ProductCart();
                            product.Id = reader.GetInt32(reader.GetOrdinal("Id"));
                            product.Id_Prodotto = reader.GetInt32(reader.GetOrdinal("Id_prodotto"));
                            product.Qta = reader.GetInt32(reader.GetOrdinal("Qta"));

                            return product;
                        }

                    }
                }
            }
            return null;
        }




        public void InsertProduct(ProductCart p)
        {

            if (GetCartProduct(p.Id_Prodotto) == null)
            {
                string query = @"
INSERT INTO [dbo].[Cart] ([Id_prodotto]
      ,[Qta])
VALUES (@id, @qta);";
                using (SqlConnection connection = new SqlConnection(this.connectionString))
                {
                    connection.Open();

                    using (SqlCommand command = new SqlCommand(query, connection))
                    {
                        command.Parameters.Add(new SqlParameter("id", p.Id_Prodotto));
                        command.Parameters.Add(new SqlParameter("qta", p.Qta));
                        command.ExecuteNonQuery();
                    }
                }
            }
            else
            {

                string query = @"
UPDATE [dbo].[Cart]
   SET [Qta] = @Qta
 WHERE Id_Prodotto = @Id_Prodotto
";
                using (SqlConnection connection = new SqlConnection(this.connectionString))
                {
                    connection.Open();

                    using (SqlCommand command = new SqlCommand(query, connection))
                    {
                        command.Parameters.Add(new SqlParameter("Id_Prodotto", p.Id_Prodotto));
                        command.Parameters.Add(new SqlParameter("Qta", p.Qta));
                        command.ExecuteNonQuery();
                    }
                }
            }
        }




        public void DeleteByCart(ProductCart p)
        {
            string query = @"
DELETE FROM [dbo].[Cart] 
WHERE Id_Prodotto=@Id;";
            using (SqlConnection connection = new SqlConnection(this.connectionString))
            {
                connection.Open();

                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    command.Parameters.Add(new SqlParameter("id", p.Id_Prodotto));
                    command.ExecuteNonQuery();
                }
            }
        }
    }
}