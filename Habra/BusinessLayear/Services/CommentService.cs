using BusinessLayer.Interfaces;
using DataLayer.Interfaces;
using DataLayer.Models;
using System.Collections.Generic;

namespace BusinessLayear.Services
{
    public class CommentService : ICommentService
    {

        private readonly IRepository<Comment> commentRepository;
        private readonly IUnitOfWork unitOfWork;

        public CommentService( IRepository<Comment> commentRepository, IUnitOfWork unitOfWork )
        {
            this.commentRepository = commentRepository;
            this.unitOfWork = unitOfWork;
        }

        public void Commit()
        {
            unitOfWork.Commit();
        }

        public void Create( Comment item )
        {
            commentRepository.Create( item );
        }

        public void Delete( Comment item )
        {
            commentRepository.Delete( item );
        }

        public Comment Get( int id )
        {
            return commentRepository.Get( id );
        }

        public IEnumerable<Comment> GetAll()
        {
            return commentRepository.GetAll();
        }        

        public void Update( Comment item )
        {
            commentRepository.Update( item );
        }
    }
}
