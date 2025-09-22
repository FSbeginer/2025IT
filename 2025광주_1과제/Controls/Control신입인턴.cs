using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제.Controls
{
    public partial class Control신입인턴 : UserControl
    {
        public jobposting jobposting { get; set; }
        public Control신입인턴()
        {
            InitializeComponent();
        }

        private void Control신입인턴_Load(object sender, EventArgs e)
        {
            int[] no = Hp.user.u_sc.Split(',').Select(x => int.Parse(x)).ToArray();
            if (no.Contains(jobposting.c_no))
            {
                pictureBox1.Image = Properties.Resources.save2;
            }
            else
            {
                pictureBox1.Image = Properties.Resources.save1;
            }
            label1.Text = jobposting.company.c_name;
            label2.Text = jobposting.jp_title;
            label3.Text = $"{jobposting.location.l_name} {jobposting.location.l_name} | 회사{(jobposting.jp_sc == 1 ? "내규" : "외규")} | {(jobposting.jp_situation == 1 ? "신입" : "경력")} | {jobposting.occupation.o_name} | {jobposting.education.e_name}";
            int left = (jobposting.start_date - DateTime.Now.Date).Days;
            label4.Text = Math.Abs(left)+"일 "+(left>0?"후":"전");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            (FindForm() as View.Form신입인턴).showPage(new View.Form채용공고() { jobposting = jobposting});
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                db.user.Attach(Hp.user);

                List<int> ints = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                if (ints.Contains(jobposting.c_no))
                {
                    ints.Remove(jobposting.c_no);
                    Hp.user.u_sc = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    ints.Add(jobposting.c_no);
                    Hp.user.u_sc = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save2;
                }

                db.SaveChanges();
            }
        }
    }
}
