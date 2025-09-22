using _2025광주_1과제.Controls;
using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Configuration;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form신입인턴 : _2025광주_1과제.Model.BF
    {
        List<string> name = new List<string>();
        List<List<int>> nums = new List<List<int>>();
        Label updown = new Label();
        int height;

        public Form신입인턴()
        {
            InitializeComponent();
        }

        private void Form신입인턴_Load(object sender, EventArgs e)
        {
            updown = new Label
            {
                Width = 50,
                Text = "△",
                Dock = DockStyle.Right,
                TextAlign = ContentAlignment.MiddleCenter
            };
            label4.Controls.Add(updown);
            updown.Click += Updown_Click;
            height = panel1.Height;

            for (int i = 0; i < 4; i++)
            {
                nums.Add(new List<int>());
            }

            using (var db = new Model.placementEntities())
            {
                foreach (var loc in db.location)
                {
                    Label lbl = new Label
                    {
                        TextAlign = ContentAlignment.MiddleCenter,
                        Width = flowLayoutPanel2.Width - 20,
                        Height = flowLayoutPanel2.Height / 3,
                        Text = loc.l_name,
                        Tag = (0, loc.l_no),
                        Margin = new Padding(0)
                    };
                    flowLayoutPanel2.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                foreach (var occ in db.occupation)
                {
                    Label lbl = new Label
                    {
                        TextAlign = ContentAlignment.MiddleCenter,
                        Width = flowLayoutPanel2.Width - 20,
                        Height = flowLayoutPanel2.Height / 3,
                        Text = occ.o_name,
                        Tag = (1, occ.o_no),
                        Margin = new Padding(0)
                    };
                    flowLayoutPanel3.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                foreach (var edu in db.education)
                {
                    Label lbl = new Label
                    {
                        TextAlign = ContentAlignment.MiddleCenter,
                        Width = flowLayoutPanel2.Width - 20,
                        Height = flowLayoutPanel2.Height / 3,
                        Text = edu.e_name,
                        Tag = (2, edu.e_no),
                        Margin = new Padding(0)
                    };
                    flowLayoutPanel4.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                foreach (var jt in db.jobtype)
                {
                    Label lbl = new Label
                    {
                        TextAlign = ContentAlignment.MiddleCenter,
                        Width = flowLayoutPanel2.Width - 20,
                        Height = flowLayoutPanel2.Height / 3,
                        Text = jt.jt_name,
                        Tag = (3, jt.jt_no),
                        Margin = new Padding(0)
                    };
                    flowLayoutPanel5.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                comboBox1.SelectedIndex = 0;
                comboBox2.SelectedIndex = 0;
                chage();
                load();
            }
        }

        private void Lbl_Click(object sender, EventArgs e)
        {
            Label lbl = sender as Label;
            ToggleSelect(lbl.Text, lbl);
            chage();
        }

        private void ToggleSelect(string item, Label lbl)
        {
            if (name.Contains(item))
            {
                name.Remove(item);
                (int idx, int no) = ((int, int))lbl.Tag;
                nums[idx].Remove(no);
                lbl.ForeColor = Color.Black;
            }
            else
            {
                if (name.Count == 10)
                {
                    Hp.msgErr("조건은 추가 할 수 없습니다.");
                }
                else
                {
                    name.Add(item);
                    (int idx, int no) = ((int, int))lbl.Tag;
                    nums[idx].Add(no);
                    lbl.ForeColor = Color.Gold;
                }
            }
        }

        private void chage()
        {
            flowLayoutPanel1.Controls.Clear();
            int w = flowLayoutPanel1.Width / 5, h = flowLayoutPanel1.Height / 2;

            foreach (var item in name)
            {
                Label label = new Label()
                {
                    Size = new Size(w, h),
                    Text = item,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Margin = new Padding(0)
                };
                flowLayoutPanel1.Controls.Add(label);
            }

            using (var db = new Model.placementEntities())
            {
                var list = db.jobposting.AsEnumerable().Where(x => (nums[0].Count > 0 ? nums[0].Contains(x.l_no) : true) && (nums[1].Count > 0 ? nums[1].Contains(x.o_no) : true) && (nums[2].Count > 0 ? nums[2].Contains(x.e_no) : true) && (nums[3].Count > 0 ? nums[3].Contains(x.jt_no) : true));
                button1.Text = $"예상결과 {list.Count()}건 검색하기";
            }
        }

        private void Updown_Click(object sender, EventArgs e)
        {
            if (updown.Text == "△")
            {
                updown.Text = "▽";
                panel1.Height = 0;
            }
            else
            {
                updown.Text = "△";
                panel1.Height = height;
            }
        }


        private void pictureBox1_Click(object sender, EventArgs e)
        {
            foreach (var item in flowLayoutPanel2.Controls.Cast<Label>())
            {
                item.ForeColor = Color.Black;
            }
            foreach (var item in flowLayoutPanel3.Controls.Cast<Label>())
            {
                item.ForeColor = Color.Black;
            }
            foreach (var item in flowLayoutPanel4.Controls.Cast<Label>())
            {
                item.ForeColor = Color.Black;
            }
            foreach (var item in flowLayoutPanel5.Controls.Cast<Label>())
            {
                item.ForeColor = Color.Black;
            }
            name.Clear();
            foreach (var item in nums)
            {
                item.Clear();
            }
            chage();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            load();
        }

        private void load()
        {
            flowLayoutPanel6.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                int cmb1 = comboBox1.SelectedIndex;
                int cmb2 = comboBox2.SelectedIndex;
                DateTime date = DateTime.Now.Date.AddDays(-7);
                var list = db.jobposting.AsEnumerable().Where(x=>(cmb1>0? x.company.c_information==cmb1-1:true) && (cmb2 == 0 ? true :cmb2 == 1 ? (date <= x.start_date&&x.start_date <= DateTime.Now.Date): (x.start_date < date&&DateTime.Now >= x.start_date)) && (nums[0].Count > 0 ? nums[0].Contains(x.l_no) : true) && (nums[1].Count > 0 ? nums[1].Contains(x.o_no) : true) && (nums[2].Count > 0 ? nums[2].Contains(x.e_no) : true) && (nums[3].Count > 0 ? nums[3].Contains(x.jt_no) : true));
                foreach (var item in list)
                {
                    Control신입인턴 con = new Control신입인턴 { jobposting = item };
                    flowLayoutPanel6.Controls.Add(con);
                }
            }
        }
    }
}
