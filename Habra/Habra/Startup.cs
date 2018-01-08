using BusinessLayear.Services;
using BusinessLayer.Services;
using BusinessLayer.Interfaces;
using DataLayer;
using DataLayer.Interfaces;
using DataLayer.Models;
using DataLayer.Repositories;
using Habra.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Newtonsoft.Json.Serialization;
using Habra.Filters;

namespace Habra
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }
        
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddScoped( typeof( IManualRepository ), typeof( ManualRepository ) );
            services.AddScoped( typeof( IRepository<> ), typeof( BaseRepository<> ) );
            services.AddScoped( typeof( IUnitOfWork ), typeof( UnitOfWork ) );
            services.AddScoped( typeof( IManualService ), typeof( ManualService ) );
            services.AddScoped( typeof( IPictureService ), typeof( PictureService ) );
            services.AddScoped( typeof( ICommentService ), typeof( CommentService ) );

            services.AddDbContext<ApplicationDbContext>(options =>
                options.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));

            services.AddIdentity<ApplicationUser, IdentityRole>( config =>
            {
                config.SignIn.RequireConfirmedEmail = true;
            } )
                .AddEntityFrameworkStores<ApplicationDbContext>()
                .AddDefaultTokenProviders();
            services.AddAuthentication().AddFacebook( facebookOptions =>
            {
                facebookOptions.AppId = "1887600547920280";
                facebookOptions.AppSecret = "45de76c97dbd65596aed1980af789b7f";
            } )
            .AddTwitter( twitterOptions =>
            {
                twitterOptions.ConsumerKey = "74w8ypBBSQLGAStz0L0V17y9F";
                twitterOptions.ConsumerSecret = "XABG88r0MMERX6bm3O7NoFqTJJaiM3ahHzYIyrEMibEVDZE1Zx";
            } )
            .AddGoogle( googleOptions =>
            {
                googleOptions.ClientId = "478258097215-60qkjcg3uhn01nsksae3635og9qlloh7.apps.googleusercontent.com";
                googleOptions.ClientSecret = "kUkSxiUhTeX2HDYs1mot_HGI";
            } );
            services.Configure<MvcOptions>( options =>
            {
                options.Filters.Add( new RequireHttpsAttribute() );
            } );
            services.AddKendo();
            services.AddTransient<IEmailSender, EmailSender>();
            services.AddMvc( options => options.Filters.Add( typeof( BlockUsers ) ))
                .AddJsonOptions( options => options.SerializerSettings.ContractResolver = new DefaultContractResolver() );
        }
        
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseBrowserLink();
                app.UseDatabaseErrorPage();
            }
            else
            {
                app.UseExceptionHandler( "/Wall/Error" );
            }
            app.UseKendo( env );
            app.UseStaticFiles();
            app.UseAuthentication();
            app.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "default",
                     template: "{controller=Wall}/{action=ShowPosts}/{id?}" );
            } );
        }
    }
}
