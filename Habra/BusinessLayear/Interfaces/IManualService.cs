using DataLayer.Models;
using System;
using System.Collections.Generic;

namespace BusinessLayer.Interfaces
{
    public interface IManualService : IService<Manual>
    {
        List<Manual> GetUserPosts( ApplicationUser user );
        List<Manual> GetPostsByTime( DateTime date );
    }
}
