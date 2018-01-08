using Microsoft.AspNetCore.Mvc.Filters;
using System;

namespace Habra.Filters
{
    public class BlockUsers : Attribute, IActionFilter
    {
        public void OnActionExecuting( ActionExecutingContext context )
        {
            if(context.HttpContext.User.IsInRole("Block"))
            {
                context.HttpContext.Response.Redirect( "~/Account/AccesDenied" );
            }
        }

        public void OnActionExecuted( ActionExecutedContext context )
        {

        }
    }
}
