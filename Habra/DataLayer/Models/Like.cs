namespace DataLayer.Models
{
    public class Like
    {
        public int LikeId { get; set; }
        public ApplicationUser Owner { get; set; }
        public Manual Post { get; set; }
    }
}
