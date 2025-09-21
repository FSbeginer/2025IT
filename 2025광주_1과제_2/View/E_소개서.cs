using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class E_소개서 : _2025광주_1과제_2.Template.BF
    {
        public E_소개서()
        {
            InitializeComponent();
            panel2.MouseWheel += Panel2_MouseWheel;
        }

        private void Panel2_MouseWheel(object sender, MouseEventArgs e)
        {
            int dx = e.Delta > 0 ? 10 : -10;
            if (pps2.Count == 0 || pps2[0].Top + dx > 0 || pps2.Last().Bottom + dx < panel2.Height) return;
            foreach (var item in pps2)
            {
                item.Top += dx;
            }
        }

        private void E_소개서_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var list = db.occupation.Select(x => x.o_name).ToList();
                list.Insert(0, "전체");
                comboBox1.DataSource = list;
                comboBox2.SelectedIndex = 0;
            }
            areaA();
            areaB();
        }

        private void areaB()
        {
            panel2.Controls.Clear();
            pps2.Clear();
            using (var db = new Model.placementEntities())
            {
                var list = db.selfintroduction.Where(x => (comboBox1.SelectedIndex == 0 ? true : x.o_no == comboBox1.SelectedIndex)).ToList();
                if (comboBox2.SelectedIndex == 1)
                {
                    list = list.OrderByDescending(x => x.si_no).ToList();
                }
                else if (comboBox2.SelectedIndex == 2)
                {
                    list = list.OrderBy(x => x.u_no).ToList();
                }

                int w = (panel1.Width - 30) / 3;
                int h = panel1.Height - 20;
                foreach (var item in list)
                {
                    소개서 pp = new 소개서 { intro = item };
                    pp.Size = new Size(w, h);
                    pp.Location = new Point((w + 10) * (pps2.Count % 3), (h + 10) * (pps2.Count / 3));
                    panel2.Controls.Add(pp);
                    pps2.Add(pp);
                }
            }
        }

        List<소개서> pps = new List<소개서>();
        List<소개서> pps2 = new List<소개서>();
        private void areaA()
        {
            using (var db = new Model.placementEntities())
            {
                var list = db.selfintroduction.Where(x => x.u_no == Hp.user.u_no).ToList();
                int w = (panel1.Width - 30) / 3;
                int h = panel1.Height - 20;
                foreach (var item in list)
                {
                    소개서 pp = new 소개서 { intro = item, Margin = new Padding(0) };
                    pp.Size = new Size(w, 100);
                    pp.Location = new Point((w + 10) * pps.Count, 0);
                    panel1.Controls.Add(pp);
                    eventAdd(pp);
                    pps.Add(pp);
                }
            }
        }

        private void eventAdd(소개서 pp)
        {
            pp.MouseDown += Pp_MouseDown;
            pp.MouseUp += Pp_MouseUp;
            pp.MouseMove += Pp_MouseMove;
            foreach (Label item1 in pp.Controls)
            {
                item1.MouseDown += Pp_MouseDown;
                item1.MouseUp += Pp_MouseUp;
                item1.MouseMove += Pp_MouseMove;
            }
        }

        private void Pp_MouseMove(object sender, MouseEventArgs e)
        {
            if (drag)
            {
                int dx = e.X - x;
                if (pps.Count == 0 || pps[0].Left + dx > 0 || pps.Last().Right + dx < panel1.Width) return;
                foreach (var item in pps)
                {
                    item.Left += dx;
                }
            }
        }

        private void Pp_MouseUp(object sender, MouseEventArgs e)
        {
            drag = false;
        }

        bool drag = false;
        int x;
        private void Pp_MouseDown(object sender, MouseEventArgs e)
        {
            drag = true;
            x = e.X;
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            areaB();
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            areaB();
        }
    }
}
