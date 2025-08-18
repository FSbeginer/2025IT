using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class SelectTime : _2025경기_1과제.Template.BF
    {
        Label[] txt = new Label[7];
        DateTime now = DateTime.Now.Date;


        public SelectTime()
        {
            InitializeComponent();
            Hp.selDate = now;
            Hp.selTime = default;
        }

        private void SelectTime_Load(object sender, EventArgs e)
        {
            string[] week = "일,월,화,수,목,금,토".Split(',');
            for (int i = 0; i < 7; i++)
            {
                Label lbl = new Label
                {
                    ForeColor = i == 0 || i == 6 ? Color.Red : Color.Black,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill,
                    Text = week[i]
                };
                tableLayoutPanel1.Controls.Add((Label)lbl);
            }
            for (int i = 0; i < 7; i++)
            {
                txt[i] = new Label
                {
                    ForeColor = i == 0 || i == 6 ? Color.Red : Color.Black,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill
                };
                txt[i].Click += SelectTime_Click;
                tableLayoutPanel1.Controls.Add((Label)txt[i]);
            }
            using (var db = new Model.SkillCinemaEntities())
            {
                TimeSpan time = TimeSpan.FromHours(8);
                Button[] btn = { button1, button2, button3, button4, button5, button6 };
                for (global::System.Int32 i = 0; i < 6; i++)
                {
                    btn[i].Text = time.ToString("hh\\:mm");
                    btn[i].Tag = time;
                    time = time.Add(TimeSpan.FromHours((Hp.selMovie.rtime + 59) / 60));
                }
            }
            loadDate();
            SetBtnActive();
        }

        private void loadDate()
        {
            int start = (int)now.DayOfWeek;
            for (int i = 0; i < 7; i++)
            {
                DateTime date = now.AddDays(i - start);
                txt[i].Text = date.ToString("MM\\/dd");
                txt[i].Tag = date;
                txt[i].ForeColor = Color.Black;
                if(date==Hp.selDate) txt[i].ForeColor = Color.Green;
            }
        }

        private void SelectTime_Click(object sender, EventArgs e)
        {
            var lbl = sender as Label;
            ReSetLabelForecolor();
            lbl.ForeColor = Color.Green;

            DateTime date = (DateTime)lbl.Tag;
            Hp.selDate = date;
            SetBtnActive();
        }

        private void SetBtnActive()
        {
            Hp.selTime = default;
            Button[] btn = { button1, button2, button3, button4, button5, button6 };
            for (int i = 0; i < 6; i++)
            {
                btn[i].BackColor= Hp.selDate.Add((TimeSpan)btn[i].Tag) > DateTime.Now ? Color.LightSkyBlue : Color.Silver;
            }
        }

        private void ReSetLabelForecolor()
        {
            for (int i = 0; i < 7; i++)
            {
                txt[i].ForeColor = i == 0 || i == 6 ? Color.Red : Color.Black;
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            now = now.AddDays(7);
            loadDate();
            label1.Enabled = true;
        }

        private void label1_Click(object sender, EventArgs e)
        {
            now = now.AddDays(-7);
            loadDate();
            label1.Enabled = now.Date > DateTime.Now.Date;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var btn = sender as Button;
            if (btn.BackColor == Color.Silver) return;
            btn.BackColor = Color.Red;
            Hp.selTime = (TimeSpan)btn.Tag;
        }

        private void SelectTime_FormClosed(object sender, FormClosedEventArgs e)
        {
            if(Hp.selDate != default && Hp.selTime != default)
            {
                ShowPage(new View.Reservation());
            }
            else
            {
                ShowPage("메인");
            }
        }
    }
}
