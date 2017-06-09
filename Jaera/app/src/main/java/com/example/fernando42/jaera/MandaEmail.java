package com.example.fernando42.jaera;
import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import java.util.Properties;
import android.content.Context;
import android.os.AsyncTask;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MandaEmail  extends AsyncTask<Void, Void, Boolean> {
        private Context context;
        private Session session;
        private String email;
        private AlertDialog ad;
        private ProgressDialog progressDialog;
        public MandaEmail(Context context, String email){
            this.context = context;
            this.email = email;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context, "Mandando Mensagem", "Mandando", false, false);
        }
        @Override
        protected void onPostExecute(Boolean sucesso) {
            super.onPostExecute(sucesso);
            progressDialog.dismiss();
            if (sucesso){
                Toast.makeText(context, "Mensagem Enviada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Impossivel conectar com email", Toast.LENGTH_LONG).show();
            }


        }
        @Override
        protected Boolean doInBackground(Void... params){
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            Pessoa p= new Pessoa();
                            return new PasswordAuthentication(p.getEmail1(),p.getSenha());
                        }
                    });
            try {
                MimeMessage mm = new MimeMessage(session);
                mm.setFrom(new InternetAddress(email));
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                Mensagem m=new Mensagem();
                mm.setSubject(m.getTitulo());
                mm.setText(m.getTexto());
                Transport.send(mm);
                return true;

            } catch (MessagingException e) {

                e.printStackTrace();
                return false;

            }

        }
    }

