using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class MovieInfo : _2025경기_1과제.Template.BF
    {
        public movie Movie { get; set; }
        public MovieInfo()
        {
            InitializeComponent();
        }

        private void MovieInfo_Load(object sender, EventArgs e)
        {
            label4.Text = Movie.mname;
            label5.Text = "감독 : " + Movie.director;
            label6.Text = "배우 : "+Movie.actor;
            label7.Text = "개봉일 : " + Movie.sdate.ToString("yyyy-MM-dd");
            label8.Text = "상영시간 : "+Movie.rtime+"분";
            pictureBox1.Image = GetImage("Image/" + Movie.mno + ".jpg");
            label13.Text = Movie.age.age1+"";
            panel3.BackColor = Movie.ano == 1 ? Color.Blue : Movie.ano == 2 ? Color.Yellow : Movie.ano == 3 ? Color.Green : Color.Red;
            using (var db = new Model.SkillCinemaEntities())
            {
                db.movie.Attach(Movie);
                var cnt = db.reservation.Where(x => x.mno == Movie.mno).Count();
                var rank = db.reservation.GroupBy(x => x.mno).Count(g => g.Count() > cnt) +1;
                label9.Text = rank + "위 / 전체";
                var rank2 = db.reservation.Where(x => x.movie.gno == Movie.genre.gno).GroupBy(x => x.mno).Count(g => g.Count() > cnt) + 1;
                label10.Text = rank2 + "위 / " + Movie.genre.gname;

                Bitmap man = new Bitmap(Properties.Resources.man1, pictureBox2.ClientSize);
                Bitmap girl = new Bitmap(Properties.Resources.girl1, pictureBox2.ClientSize);
                pictureBox3.Image = man;
                pictureBox4.Image = girl;
                var per = (int)((double)db.reservation.Where(x => x.mno == Movie.mno&&x.users.gender==1).Count()/cnt*100);
                pictureBox3.Height = (pictureBox5.Height / 100) * (100-per);
                pictureBox4.Height = (pictureBox5.Height / 100) * per;
                label11.Text = $"남({per}%)";
                label12.Text = $"여({100 - per }%)";

                double? avg = db.review.Where(x => x.reservation.mno == Movie.mno).Average(x=>(int?)x.likecount);

                if (avg.HasValue)
                {
                    string txt = "";
                    for (global::System.Int32 i = 0; i < (int)(avg+0.5); i++)
                    {
                        txt += "★";
                    }
                    label2.Text = txt;
                    label3.Text = $"{avg:N1}";
                }
                else
                {
                    label3.Text = "0점";
                    label2.Text = "";
                }


                var reser = db.reservation.Where(x => x.mno == Movie.mno);
                int[] cnts = new int[9];
                foreach (var item in reser)
                {
                    int age = Hp.GetAge(item.users.birth)/10;
                    if (age < 1 || age > 8) continue;
                    cnts[age]++;
                }
                for (global::System.Int32 i = 1; i <= 8; i++)
                {
                    chart1.Series[0].Points.AddXY(i + "0대", (double)cnts[i]/cnts.Sum());
                }
            }

        }

        private void panel3_Paint(object sender, PaintEventArgs e)
        {
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddEllipse(panel3.ClientRectangle);
                panel3.Region = new Region(path);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Hp.selMovie = Movie;
            ShowPage(new SelectTime());
        }
    }
}
