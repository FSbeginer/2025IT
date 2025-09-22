using _2025광주_1과제.Controls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form소개서 : _2025광주_1과제.Model.BF
    {
        List<Control소개서> myCon = new List<Control소개서>();
        List<Control소개서> allCon = new List<Control소개서> { };

        public Form소개서()
        {
            InitializeComponent();
        }

        private void Form소개서_Load(object sender, EventArgs e)
        {
            panel2.MouseWheel += Panel2_MouseWheel;
            areaA();
        }

        private void areaA()
        {
            using (var db = new Model.placementEntities())
            {
                var myInt = db.selfintroduction.Where(x => Hp.user.u_no == x.u_no).ToList();
                for (global::System.Int32 i = 0; i < myInt.Count; i++)
                {
                    Control소개서 control = new Control소개서 { selfintroduction = myInt[i] };
                    control.Location = new Point(210 * i, 0);
                    eventAdd(control);
                    myCon.Add(control);
                    panel1.Controls.Add(control);
                }
                var list = db.occupation.Select(x => x.o_name).ToList();
                list.Insert(0, "전체");
                comboBox1.DataSource = list;
                comboBox2.SelectedIndex = 0;
            }
        }

        private void eventAdd(Control소개서 control)
        {
            foreach (Control item in control.Controls)
            {
                item.MouseDown += panel1_MouseDown;
                item.MouseUp += panel1_MouseUp;
                item.MouseMove += panel1_MouseMove;
            }
            control.MouseDown += panel1_MouseDown;
            control.MouseUp += panel1_MouseUp;
            control.MouseMove += panel1_MouseMove;
        }

        private void Panel2_MouseWheel(object sender, MouseEventArgs e)
        {
            int dx = e.Delta > 0 ? 10 : -10;
            if (allCon[0].Top + dx > 0 || allCon.Last().Bottom + dx < panel2.Height - 20) return;
            foreach (var item in allCon)
            {
                item.Top += dx;
            }
        }

        Point p;
        bool move = false;
        private void panel1_MouseDown(object sender, MouseEventArgs e)
        {
            p = e.Location;
            move = true;
        }

        private void panel1_MouseUp(object sender, MouseEventArgs e)
        {
            move = false;
        }

        private void panel1_MouseMove(object sender, MouseEventArgs e)
        {
            if (move)
            {
                int dx = e.X - p.X;
                if (OutofRange(dx)) return;
                foreach (var item in myCon)
                {
                    item.Left += dx;
                }
            }

        }

        private bool OutofRange(int dx)
        {
            return (myCon[0].Left + dx > 0) || (myCon.Last().Right + dx < panel1.Width);
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            allCon.Clear();
            panel2.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                var all = db.selfintroduction.ToList();
                if (comboBox1.SelectedIndex != 0)
                    all = all.Where(x => x.o_no == comboBox1.SelectedIndex).ToList();
                if (comboBox2.SelectedIndex == 1)
                    all = all.OrderByDescending(x => x.u_no).ToList();

                for (global::System.Int32 i = 0; i < all.Count; i++)
                {
                    Control소개서 control = new Control소개서 { selfintroduction = all[i] };
                    control.Location = new Point(210 * (i % 3), 170 * (i / 3));
                    control.MouseWheel += Panel2_MouseWheel;
                    allCon.Add(control);
                    panel2.Controls.Add(control);
                }
            }

        }
    }
}
