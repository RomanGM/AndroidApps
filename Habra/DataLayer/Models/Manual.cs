using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace DataLayer.Models
{
    public class Manual
    {
        public int ManualId { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public string Synopsis { get; set; }
        [Required]
        public string Content { get; set; }
        [Required]
        public DateTime Date { get; set; }
        public int PictureId { get; set; }
        public string OwnerId { get; set; }
        public Theme Theme { get; set; }
        public Picture Picture { get; set; }
        public ApplicationUser Owner { get; set; }
        public virtual List<Tag> Tags { get; set; }
        public virtual List<Comment> Comments { get; set; }
        public virtual List<Like> Likes { get; set; }
    }
}
