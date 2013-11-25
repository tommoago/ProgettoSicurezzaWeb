using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(ProgettoSicurezzaWeb.Startup))]
namespace ProgettoSicurezzaWeb
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
