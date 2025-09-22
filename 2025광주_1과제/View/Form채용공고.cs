using _2025광주_1과제.Model;
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
    public partial class Form채용공고 : _2025광주_1과제.Model.BF
    {
        public jobposting jobposting { get; set; }
        public Form채용공고()
        {
            InitializeComponent();
        }

        private void Form채용공고_Load(object sender, EventArgs e)
        {

            using (var db = new Model.placementEntities())
            {
                db.jobposting.Add(jobposting);
                label1.Text = jobposting.jp_title;
                label2.Text = jobposting.company.c_name;
                label9.Text = jobposting.jobtype.jt_name;
                label10.Text = jobposting.location.l_name;
                label11.Text = "회사" + (jobposting.jp_sc == 1 ? "내규" : "외규");
                label12.Text = jobposting.jp_situation == 1 ? "신입" : "경력";
                label13.Text = jobposting.education.e_name;
                label14.Text = jobposting.occupation.o_name;
                var d = db.applicationemployment.Any(x => x.u_no == Hp.user.u_no && x.jp_no == jobposting.jp_no);
                if (d)
                    button1.Text = "지원 취소";
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var user = db.user.Find(Hp.user.u_no);

                List<int> ints = user.u_sc.Split(',').Select(int.Parse).ToList();
                if (ints.Contains(jobposting.c_no))
                {
                    ints.Remove(jobposting.c_no);
                    user.u_sc = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save1;
                }
                else
                {
                    ints.Add(jobposting.c_no);
                    user.u_sc = string.Join(",", ints);
                    pictureBox1.Image = Properties.Resources.save2;
                }

                db.SaveChanges();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var d = db.applicationemployment.FirstOrDefault(x => x.u_no == Hp.user.u_no && x.jp_no == jobposting.jp_no);
                if (d != null)
                {
                    db.applicationemployment.Remove(d);
                    db.SaveChanges();
                    button1.Text = "지원";
                    return;
                }
                else
                {
                    if (jobposting.start_date > DateTime.Now.Date)
                    {
                        Hp.msgErr("채용 공고를 시작하지 않았습니다.");
                        return;
                    }
                    if (jobposting.jp_situation == 0 && Hp.user.u_situation == 1)
                    {
                        Hp.msgErr("신입이 경력에 지원 할 수 없습니다.");
                        return;
                    }
                    if (jobposting.e_no > Hp.user.e_no)
                    {
                        Hp.msgErr("학력 미달입니다.");
                        return;
                    }
                    if (jobposting.o_no != Hp.user.o_no)
                    {
                        Hp.msgErr("직종이 다릅니다.");
                        return;
                    }
                    var apply = new applicationemployment();
                    apply.jp_no = jobposting.jp_no;
                    apply.u_no = Hp.user.u_no;
                    db.applicationemployment.Add(apply);
                    db.SaveChanges();
                    button1.Text = "지원 취소";
                }
            }

        }
    }
}
