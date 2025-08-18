using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class Reservation : _2025경기_1과제.Template.BF
    {
        FlowLayoutPanel[] flowLayoutPanels = new FlowLayoutPanel[10];

        public Reservation()
        {
            InitializeComponent();
        }

        private void Reservation_Load(object sender, EventArgs e)
        {
            using (var db = new Model.SkillCinemaEntities())
            {
                db.movie.Attach(Hp.selMovie);
                for (int i = 0; i < 10; i++)
                {
                    flowLayoutPanels[i] = new FlowLayoutPanel()
                    {
                        Dock = DockStyle.Fill,
                    };
                    tableLayoutPanel1.Controls.Add(flowLayoutPanels[i]);
                }

                label1.Text = $"{Hp.selDate:yyyy-MM-dd}  {Hp.selMovie.theater.div}관: {Hp.selMovie.mname} {Hp.selTime}";
                if (Hp.selTime < TimeSpan.FromHours(10))
                {
                    label13.ForeColor = Color.Black;
                    lblMorningSale.ForeColor = Color.Black;
                }
                for (int i = 0; i < Hp.selMovie.theater.seat; i++)
                {
                    string txt = (char)('A' + i / 10) + $"{i%10 + 1:D2}";
                    Label lbl = new Label
                    {
                        Text = txt,
                        BackColor = Color.LightSkyBlue,
                        ForeColor = Color.White,
                        AutoSize = true,
                        MinimumSize = new Size(tableLayoutPanel1.Width / 10-20, 20),
                        TextAlign = ContentAlignment.MiddleCenter,
                        Margin = new Padding(10)
                    };
                    flowLayoutPanels[i%10].Controls.Add(lbl);
                }
                button7.Enabled = Hp.user.seatcount >= 5; 
            }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            ShowPage(new SelectTime());
        }

        private void Reservation_FormClosed(object sender, FormClosedEventArgs e)
        {
            ShowPage("상세정보");
        }

        private void button7_Click(object sender, EventArgs e)
        {
            var c = new Sale();
            c.FormClosed += (s, e2) =>
            {
                if (c.radioButton1.Checked) lblCoupon.Text = "무료쿠폰";
                else lblCoupon.Text = "40% 할인권";
            };
            ShowPage(c);
        }
        
    }
}
