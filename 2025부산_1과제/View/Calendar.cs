using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class Calendar : _2025부산_1과제.Template.BF
    {
        public List<DateTime> dayList { get; set; }
        public Label[] labels = new Label[42];
        DateTime now = DateTime.Now.Date;
        public DateTime selDate = DateTime.Now.Date;
        public Calendar()
        {
            InitializeComponent();
        }

        private void Calendar_Load(object sender, EventArgs e)
        {
            var txt = "일 월 화 수 목 금 토".Split(' ');
            for (int i = 0; i < 7; i++)
            {
                Label label = new Label()
                {
                    Text = txt[i],
                    ForeColor = i==0? Color.Red : i == 6 ? Color.DodgerBlue : Color.Black,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill,
                };   
                tableLayoutPanel1.Controls.Add(label);
            }
            for (int i = 0; i < 42; i++)
            {
                labels[i] = new Label()
                {
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill,
                    ForeColor = i % 7 == 0 ? Color.Red : i % 7 == 6 ? Color.Blue : Color.Black,
                };
                labels[i].Click += Calendar_Click;
                tableLayoutPanel2.Controls.Add(labels[i]);

            }

            dateLoad();
        }

        private void dateLender()
        {
            foreach (var item in labels)
            {
                item.BackColor = Color.White;
                if((DateTime)item.Tag == selDate)
                {
                    item.BackColor = Color.Yellow;
                }
            }

        }

        private void Calendar_Click(object sender, EventArgs e)
        {
            selDate = (DateTime)(sender as Label).Tag;
            dateLender() ;
        }

        private void dateLoad()
        {
            DateTime first = new DateTime(now.Year, now.Month, 1);
            int week = (int)first.Date.DayOfWeek;
            for (int i = 0; i < 42; i++)
            {
                DateTime date = first.AddDays(i-week);
                labels[i].Tag = date;
                labels[i].Text = date.Day.ToString();
                if (first.Month != date.Month) labels[i].Text = "";
                labels[i].Enabled = dayList.Contains(date);
            }
            label1.Text = now.ToString("yyyy년 MM월");
            dateLender();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            button1.Visible = false;
            button2.Visible = true;
            now = now.AddMonths(-1);
            dateLoad();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            button1.Visible = true;
            button2.Visible = false;
            now = now.AddMonths(1);
            dateLoad();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Dispose();
        }
    }
}
