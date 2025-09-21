using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class C_신입인턴 : _2025광주_1과제_2.Template.BF
    {
        List<string> names = new List<string>();
        List<List<int>> nums = new List<List<int>>();
        private Label updown;

        public C_신입인턴()
        {
            InitializeComponent();
        }

        private void C_신입인턴_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var locs = db.location.ToList();

                int w = flowLayoutPanel1.Width - 25;
                int h = flowLayoutPanel1.Height/3;
                foreach (var item in locs)
                {
                    Label lbl = new Label
                    {
                        Tag = (0, item.l_no),
                        Size = new Size(w, h),
                        TextAlign = ContentAlignment.MiddleCenter,
                        Text = item.l_name
                    };
                    flowLayoutPanel1.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                nums.Add(new List<int>());
                var occ = db.occupation.ToList();

                foreach (var item in occ)
                {
                    Label lbl = new Label
                    {
                        Tag = (1, item.o_no),
                        Size = new Size(w, h),
                        TextAlign = ContentAlignment.MiddleCenter,
                        Text = item.o_name
                    };
                    flowLayoutPanel2.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                nums.Add(new List<int>());
                var edu = db.education.ToList();

                foreach (var item in edu)
                {
                    Label lbl = new Label
                    {
                        Tag = (2, item.e_no),
                        Size = new Size(w, h),
                        TextAlign = ContentAlignment.MiddleCenter,
                        Text = item.e_name
                    };
                    flowLayoutPanel3.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                nums.Add(new List<int>());
                var jt = db.jobtype.ToList();

                foreach (var item in jt)
                {
                    Label lbl = new Label
                    {
                        Tag = (3, item.jt_no),
                        Size = new Size(w, h),
                        TextAlign = ContentAlignment.MiddleCenter,
                        Text = item.jt_name
                    };
                    flowLayoutPanel4.Controls.Add(lbl);
                    lbl.Click += Lbl_Click;
                }
                nums.Add(new List<int>());
            }
            comboBox1.SelectedIndex = 0;
            comboBox2.SelectedIndex = 0;

            updown = new Label
            {
                Text = "△",
                AutoSize = true,
                Left = Width - 40,
                Top = 20,
            };
            Controls.Add(updown);
            updown.Click += Lbl_Click1; ;
            updown.BringToFront();
            chagne();
        }

        private void Lbl_Click1(object sender, EventArgs e)
        {
            if(updown.Text == "△")
            {
                tableLayoutPanel2.Height = 0;
                panel1.Height = 0;
                updown.Text = "▽";
            }
            else
            {
                tableLayoutPanel2.Height = 82;
                panel1.Height = 94;
                updown.Text = "△";
            }

        }

        private void Lbl_Click(object sender, EventArgs e)
        {
            Label lbl = sender as Label;
            toggleSelect(lbl);
            chagne();
        }

        private void chagne()
        {
            flowLayoutPanel6.Controls.Clear();
            int w = flowLayoutPanel6.Width / 5;
            int h = flowLayoutPanel6.Height / 2;
            foreach (var item in names)
            {
                Label lbl = new Label
                {
                    TextAlign = ContentAlignment.MiddleCenter,
                    Margin = new Padding(0),
                    Size = new Size((int)w, (int)h),
                    Text = item
                };
                flowLayoutPanel6.Controls.Add(lbl);
            }
            using (var db = new Model.placementEntities())
            {
                int idx1 = comboBox1.SelectedIndex;
                int idx2 = comboBox2.SelectedIndex;
                var list = db.jobposting.AsEnumerable().Where(x => x.end_date >= DateTime.Now).Where(x => (idx1 == 0 ? true : x.company.c_information == idx1 - 1) && (idx2 == 0 ? true : idx2 == 1 ? (DateTime.Now.Date - x.start_date).Days <= 7 : (DateTime.Now.Date - x.start_date).Days > 7) && (nums[0].Count == 0 ? true : nums[0].Contains(x.l_no)) && (nums[1].Count == 0 ? true : nums[1].Contains(x.o_no)) && (nums[2].Count == 0 ? true : nums[2].Contains(x.e_no)) && (nums[3].Count == 0 ? true : nums[3].Contains(x.jt_no))).OrderBy(x => x.end_date - DateTime.Now.Date).ToList();
                button1.Text = "예상결과 " + list.Count() + "건\n검색하기";
            }
        }

        private void toggleSelect(Label lbl)
        {
            if (names.Contains(lbl.Text))
            {
                names.Remove(lbl.Text);
                var (idx, no) = ((int, int))lbl.Tag;
                nums[idx].Remove(no);
                lbl.ForeColor = Color.Black;
            }
            else
            {
                if (names.Count == 10)
                {
                    msgErr("조건은 추가 할 수 없습니다.");
                    return;
                }

                var (idx, no) = ((int, int))lbl.Tag;
                nums[idx].Add(no);
                names.Add(lbl.Text);
                lbl.ForeColor = Color.Gold;
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            foreach (var item in flowLayoutPanel1.Controls.Cast<Label>())
            {
                item.ForeColor = Color.Black;
            }
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
            names.Clear();
            foreach (var item in nums)
            {
                item.Clear();
            }
            chagne();
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            panelLoad();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            panelLoad();
        }

        public void panelLoad()
        {
            flowLayoutPanel5.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                int idx1 = comboBox1.SelectedIndex;
                int idx2 = comboBox2.SelectedIndex;
                var list = db.jobposting.AsEnumerable().Where(x=>x.end_date >= DateTime.Now).Where(x=>
                (idx1==0?true:x.company.c_information==idx1-1)&&
                (idx2==0?true:idx2==1?(DateTime.Now.Date-x.start_date).Days<=7: (DateTime.Now.Date - x.start_date).Days > 7) && 
                (nums[0].Count==0 ?true:nums[0].Contains(x.l_no)) && (nums[1].Count==0 ?true:nums[1].Contains(x.o_no)) && 
                (nums[2].Count==0 ?true : nums[2].Contains(x.e_no)) && (nums[3].Count==0? true : nums[3].Contains(x.jt_no)))
                    .OrderBy(x=>x.end_date-DateTime.Now.Date).ToList();
                foreach (var item in list)
                {
                    신입인턴패널 pp = new 신입인턴패널 { job = item };
                    flowLayoutPanel5.Controls.Add(pp);
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            panelLoad();
        }
    }
}
