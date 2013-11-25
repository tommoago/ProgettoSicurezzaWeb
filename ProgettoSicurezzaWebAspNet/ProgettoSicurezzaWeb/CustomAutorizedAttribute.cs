
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Http;


namespace ProgettoSicurezzaWeb
{
    public class CustomAutorizedAttribute : AuthorizeAttribute
    {


        public override void OnAuthorization(System.Web.Http.Controllers.HttpActionContext actionContext)
        {
            string tokenHeader = HttpContext.Current.Request.Headers["token"];
            if (string.IsNullOrEmpty(tokenHeader))
                throw new HttpException(401, "token non presente");

            AuthenticationToken token;
            try
            {
                string obj = Crypto.DecryptStringAES(tokenHeader, "AppLeoTomLuca");
                token = JsonConvert.DeserializeObject<AuthenticationToken>(obj);

            }
            catch (Exception)
            {
                throw new HttpException(401, "token non valido");
            }
            if (token.CreationDate.AddMinutes(10) < DateTime.Now)
                throw new HttpException(401, "token scaduto");



            //base.OnAuthorization(actionContext);
        }

    }
}