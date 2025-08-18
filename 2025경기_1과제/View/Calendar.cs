using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class Calendar : _2025경기_1과제.Template.BF
    {
        public Calendar()
        {
            InitializeComponent();
        }

        public DateTime sdate, edate;
        Label[] lbls = new Label[42];
        public bool clcik;
        DateTime now = DateTime.Now.Date;

        private void Calendar_Load(object sender, EventArgs e)
        {

            for (int i = 0; i < 42; i++)
            {
                lbls[i] = new Label()
                {
                    Dock = DockStyle.Fill,
                    ForeColor = i %7==0 || i %7== 6 ? Color.Red : Color.Black,
                    BorderStyle = BorderStyle.FixedSingle,
                    Margin = new Padding(0)
                };
                tableLayoutPanel1.Controls.Add(lbls[i]);
                lbls[i].MouseClick += Calendar_MouseClick;
            }

            DateLoad();
        }

        private void Calendar_MouseClick(object sender, MouseEventArgs e)
        {
            var lbl = sender as Label;
            if (sdate != default && edate != default) sdate = edate = default;

            if (sdate == default)
            {
                sdate = (DateTime)lbl.Tag;
            }

            else edate = (DateTime)lbl.Tag;

            paintLabel();
            lbl.BackColor = Color.Gold;
        }

        private void paintLabel()
        {
            foreach (var item in lbls)
            {
                DateTime time = (DateTime)item.Tag;
                if (time >= sdate && time <= edate) item.BackColor = Color.Gold;
                else item.BackColor = Color.White;
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            now = now.AddMonths(1);
            DateLoad();
        }

        private void label1_Click(object sender, EventArgs e)
        {
            now = now.AddMonths(-11);
            DateLoad();
            label1.Enabled = now.Date != DateTime.Now.Date;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (sdate != default && edate != default)
            {
                clcik = true;
                Close();
            }
        }

        private void DateLoad()
        {
            DateTime start = new DateTime(now.Year, now.Month, 1);
            int startWeek = (int)start.DayOfWeek;

            for (int i = 0; i < 42; i++)
            {
                DateTime d = start.AddDays(i - startWeek);
                lbls[i].Enabled = d.Month == now.Month && DateTime.Now.Date <= d.Date;
                lbls[i].Tag = d;
                lbls[i].Text = d.Day+"";
            }
            label3.Text = start.ToString("yyyy년 M월");
        }
    }
}

