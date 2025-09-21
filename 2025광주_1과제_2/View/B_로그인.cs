using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class B_로그인 : _2025광주_1과제_2.Template.BF
    {
        public B_로그인()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var id = placeHolder1.Text;
            var pw = placeHolder2.Text;
            if (string.IsNullOrEmpty(id) || string.IsNullOrEmpty(pw))
            {
                msgErr("빈칸이 존재합니다.");
                return;
            }
            using (var db = new Model.placementEntities())
            {
                var user = db.user.Where(x=>x.u_id == id && x.u_pw==pw).FirstOrDefault();
                if (user != null)
                {
                    Hp.user = user;
                    startAni();
                }
                else
                {
                    var company = db.company.Where(x => x.c_id == id && x.c_pw == pw).FirstOrDefault();
                    if (company != null)
                    {
                        Hp.company = company;
                        startAni();
                    }
                    else
                    {
                        msgErr("존재하지 않은 회원입니다.");
                        return;
                    }
                }
            }
        }

        private void startAni()
        {
            placeHolder1.Enabled = false;
            placeHolder2.Enabled = false;
            button1.Visible = false;
            panel2.Visible = true;
            timer = new Timer() { Interval = 10};
            timer.Tick += Timer_Tick;
            timer.Start();
        }
        int per = 0;
        private Timer timer;

        private void Timer_Tick(object sender, EventArgs e)
        {
            per++;
            label4.Text = per+"%";
            panel2.Invalidate();
            if (per == 100)
            {
                timer.Stop();
                if (Hp.user != null)
                {
                    msgInfo(Hp.user.u_name + "님 환영합니다.");
                    Dispose();
                }
                else
                {
                    msgInfo(Hp.company.c_name + "님 환영합니다.");
                    showPage(new J_회사메인());                    
                }
            }
        }

        private void B_로그인_Load(object sender, EventArgs e)
        {

        }

        private void panel2_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            using (var path = new GraphicsPath())
            {
                path.AddEllipse(new Rectangle(0, 0, 60, 60));
                panel2.Region = new Region(path);
                var rect = new Rectangle(0, (int)(60 * (1 - per / 100.0)), 60, 60);
                g.FillRectangle(Brushes.DodgerBlue, rect);
                var rect2 = new Rectangle(25, 25, 10, 10);
                g.FillEllipse(Brushes.White, rect2);
            }
        }
    }
}
