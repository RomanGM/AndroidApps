﻿using MimeKit;
using MailKit.Net.Smtp;
using System.Threading.Tasks;

namespace Habra.Services
{
    public class EmailSender : IEmailSender
    {
        public async Task SendEmailAsync(string email, string subject, string message)
        {
            var emailMessage = new MimeMessage();

            emailMessage.From.Add( new MailboxAddress( "Администрация сайта", "gorbatovskij.roman@yandex.ru" ) );
            emailMessage.To.Add( new MailboxAddress( "", email ) );
            emailMessage.Subject = subject;
            emailMessage.Body = new TextPart( MimeKit.Text.TextFormat.Html )
            {
                Text = message
            };

            using (var client = new SmtpClient())
            {
                await client.ConnectAsync( "smtp.yandex.ru", 25, false );
                await client.AuthenticateAsync( "gorbatovskij.roman@yandex.ru", "6558342grm37800" );
                await client.SendAsync( emailMessage );
                await client.DisconnectAsync( true );
            }
        }
    }
}
