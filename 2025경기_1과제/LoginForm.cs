using _2025경기_1과제.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025경기_1과제
{
    public partial class LoginForm : BF
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            var id = textBox1.Text;
            var pw = textBox2.Text;
            if (string.IsNullOrEmpty(id) || string.IsNullOrEmpty(pw))
            {
                msgErr("빈칸이 있습니다.");
                return;
            }
            if (id == "admin" && pw == "1234")
            {
                msgInfo("관리자님 환영합니다.");
                ShowPage(new View.AdminMain());
                textBox1.Clear();
                textBox2.Clear();
                return;
            }

            using (var db = new Model.SkillCinemaEntities())
            {
                var u = db.users.FirstOrDefault(x=>x.id == id&& x.pw==pw);
                if (u != null)
                {
                    msgInfo(u.name + "님 환영합니다.");
                    Hp.user = u;
                    ShowPage(new View.Main());
                    textBox1.Clear();
                    textBox2.Clear();
                }
                else
                {
                    msgErr("일치하는 회원이 없습니다.");
                    return ;
                }
            }
        }
    }
}
