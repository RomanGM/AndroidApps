using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace DataLayer.Interfaces
{
    public interface IManualRepository : IRepository<Manual>
    {
        List<Manual> GetUserPosts( ApplicationUser user );
        List<Manual> GetPostsByTime( DateTime date );
    }
}
