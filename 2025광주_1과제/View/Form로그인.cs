using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Metadata.Edm;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form로그인 : _2025광주_1과제.Model.BF
    {
        int per;
        private Timer timer;

        public Form로그인()
        {
            InitializeComponent();
        }

        private void Form로그인_Load(object sender, EventArgs e)
        {
            placeHolder1.Msg = "아이디를 입력해주세요.";
            placeHolder2.Msg = "비밀번호를 입력해주세요.";

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (placeHolder1.textBox1.Text == "" || placeHolder2.textBox1.Text == "")
            {
                Hp.msgErr("빈칸이 존재합니다.");
            }
            else
            {
                using (var db = new Model.placementEntities())
                {
                    var user = db.user.FirstOrDefault(x => x.u_id == placeHolder1.textBox1.Text && x.u_pw == placeHolder2.textBox1.Text);
                    if (user != null)
                    {
                        setTimer();
                        Hp.user = user;
                        return;
                    }

                    var compony = db.company.FirstOrDefault(x => x.c_id == placeHolder1.textBox1.Text && x.c_pw == placeHolder2.textBox1.Text);
                    if (compony != null)
                    {
                        setTimer();
                        Hp.company = compony;
                        return;
                    }
                    else
                    {
                        Hp.msgErr("존재하지 않는 회원입니다.");
                    }
                }
            }
        }

        private void setTimer()
        {
            placeHolder1.textBox1.Enabled = false;
            placeHolder2.textBox1.Enabled = false;
            button1.Visible = false;
            panel2.Visible = true;

            timer = new Timer() { Interval = 10 };
            timer.Tick += Timer_Tick;
            timer.Start();
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            per++;
            panel2.Invalidate();
            label4.Text = per + "%";
            if (per == 100)
            {
                timer.Stop();
                if(Hp.user != null)
                {
                    Hp.msgInfo($"{Hp.user.u_name}님 환영합니다.");
                    Close();
                }
                else
                {
                    Hp.msgInfo($"{Hp.company.c_name}님 환영합니다.");
                    showPage(new View.Form회사메인());
                }
            }
        }

        private void panel2_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            int w = panel2.Width / 5;
            int h = panel2.Height / 5;
            int x = (panel2.Width - w)/2;
            int y = (panel2.Height - h)/2;  
            g.SetClip(new Rectangle(0, (int)(panel2.Height *(1-per/100.0)), panel2.Width, panel2.Height));
            g.FillEllipse(Brushes.CornflowerBlue, panel2.ClientRectangle);
            g.FillEllipse(Brushes.White, new Rectangle(x,y,w,h));
        }
    }
}
