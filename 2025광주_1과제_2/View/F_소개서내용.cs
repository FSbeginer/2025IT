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
    public partial class F_소개서내용 : _2025광주_1과제_2.Template.BF
    {
        public selfintroduction si { get; set; }
        public F_소개서내용()
        {
            InitializeComponent();
        }

        private void F_소개서내용_Load(object sender, EventArgs e)
        {
            label1.Text = "제목 : " + si.si_title;
            label2.Text = "부제목 : " + si.si_subtitle;
            label4.Text = si.si_explan;
            var list = Hp.user.u_si.Split(',').Select(int.Parse).ToList();
            if (list.Contains(si.si_no))
            {
                pictureBox1.Image = Properties.Resources.save2;
            }
            else
            {
                pictureBox1.Image = Properties.Resources.save1;
            }

            areaB();
            panel3.MouseWheel += FlowLayoutPanel1_MouseWheel;
        }

        private void FlowLayoutPanel1_MouseWheel(object sender, MouseEventArgs e)
        {
            int dx = e.Delta > 0 ? 5 : -5;
            if (pps.Count == 0 || pps[0].Top + dx > 10 || pps.Last().Bottom + dx < panel3.Height + 10) return;
            foreach (var item in pps)
            {
                item.Top += dx;
            }
        }

        List<댓글> pps = new List<댓글>();
        private void areaB()
        {
            pps.Clear();
            panel3.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                var list = db.comment.Where(x => x.si_no == si.si_no).ToList();
                foreach (var item in list)
                {
                    댓글 pp = new 댓글 { comment = item };
                    pp.Location = new Point(10, 10 + (pp.Height + 10) * pps.Count);
                    panel3.Controls.Add(pp);
                    pps.Add(pp);
                }
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                db.user.Attach(Hp.user);

                var list = Hp.user.u_si.Split(',').Select(int.Parse).ToList();
                if (list.Contains(si.si_no))
                {
                    list.Remove(si.si_no);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    list.Add(si.si_no);
                    pictureBox1.Image = Properties.Resources.save2;
                }
                Hp.user.u_sc = string.Join(",", list);
                db.SaveChanges();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var txt = textBox1.Text;
            if (string.IsNullOrEmpty(txt))
            {
                msgErr("텍스트 박세으 텍스트가 없습니다.");
                return;
            }
            using (var db = new Model.placementEntities())
            {
                comment com = new comment();
                com.cm_explan = txt;
                com.si_no = si.si_no;
                com.u_no = Hp.user.u_no;
                com.cm_date = DateTime.Now.Date;
                db.comment.Add(com);
                db.SaveChanges();
            }
            textBox1.Clear();
            areaB();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (textBox1.TextLength > 100)
            {
                textBox1.Text = textBox1.Text.Substring(0, 100);
            }
        }
    }
}
