using _2025부산_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography.Xml;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class LoginForm : _2025부산_1과제.Template.BF
    {
        User lockUser;
        private int cnt;

        public LoginForm()
        {
            InitializeComponent();
        }

        private void textBox1_Enter(object sender, EventArgs e)
        {
            (sender as TextBox).BackColor = Color.Yellow;
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
            (sender as TextBox).BackColor = Color.White;
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            label2.Text = "\U0001F5F9 ID";
        }

        private void label2_MouseClick(object sender, MouseEventArgs e)
        {
            var lbl = sender as Label;
            Label[] lbls = new Label[] { label2, label3, label4 };
            foreach (var item in lbls)
            {
                item.Text = "□ "+item.Text.Substring(2).Trim();
            }
            lbl.Text = "\U0001F5F9 "+lbl.Text.Substring(2).Trim();
            label5.Text = lbl.Text.Substring(2).Trim();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var id = textBox1.Text;
            var pw = textBox2.Text;
            if (id == "admin" && pw == "1234")
            {
                msgInfo("관리자입니다.");
                Hp.isAdmin = true;
                Close();
                return;
            }
            using (var db= new Model.ITTRAINEntities())
            {
                
                string option = label5.Text;
                User u = default;
                switch (option)
                {
                    case "ID": u = db.User.FirstOrDefault(x => x.u_id == id);
                        break;
                    case "전화번호": u = db.User.FirstOrDefault(x => x.u_tel == id);
                        break;
                    case "이메일": u = db.User.FirstOrDefault(x => x.u_email == id);
                        break;
                }
                if (u == default)
                {
                    msgErr("회원 정보가 일치하지 않습니다.");
                    return;
                }

                if (pw != u.u_pw)
                {
                    cnt++;
                    msgErr("회원 정보가 일치하지 않습니다.");
                    if (cnt == 3)
                    {
                        msgErr("비밀번호 3회 오류로 잠금 처리합니다.");
                        cnt = 0;
                        u.onoff = 0;
                        db.SaveChanges();
                    }
                    return;
                }
                
                if (u.onoff == 0)
                {
                    msgErr("잠긴 회원입니다.");
                    passWord();
                    lockUser = u;
                    tabControl1.SelectedTab = tabPage2;
                }
                else 
                {
                    Hp.user = u;
                    msgInfo(u.u_name + "님 환영합니다.");
                    Close();
                    return ;
                }
            }
        }
        private void passWord()
        {
            Random rand = new Random();
            string pw = "";
            for (int i = 0; i < 5; i++)
            {
                var c =((char)('A' + rand.Next(26))).ToString();
                if (Convert.ToBoolean(rand.Next(2)))
                {
                    c = c.ToLower();
                }
                pw += c;
            }
            label10.Text = pw;
        }

        private void label10_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            Random random = new Random();
            g.DrawLine(Pens.Black, random.Next(40), random.Next(label10.Height), label10.Width - 40 + random.Next(40), random.Next(label10.Height));
            g.DrawLine(Pens.Black, random.Next(40), random.Next(label10.Height), label10.Width - 40 + random.Next(40), random.Next(label10.Height));
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            passWord();
            label10.Invalidate();

        }

        private void button2_Click(object sender, EventArgs e)
        {
            var txt = textBox4.Text;
            if (txt == label10.Text)
            {
                using (var db = new Model.ITTRAINEntities())
                {
                    db.User.Attach(lockUser);
                    lockUser.onoff = 1;
                    db.SaveChanges();
                    tabControl1.SelectedTab = tabPage1;
                    textBox1.Clear();
                    textBox2.Clear();
                    textBox4.Clear();
                }
            }
            else
            {
                msgErr("입력란이 비어있거나 인증 문구가 틀립니다.");
            }
        }
    }
}
