using System;
using System.Collections.Generic;
using System.Text;

namespace DataLayer.Models
{
    public class Comment
    {
        public int CommentId { get; set; }
        public ApplicationUser owner { get; set; }
        public string Content { get; set; }
        public int ManualId { get; set; }
    }
}
