using _2025광주_1과제_2.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.MyControl
{
    public partial class 마이페이지 : UserControl
    {
        public 마이페이지()
        {
            InitializeComponent();
        }

        private void 마이페이지_Load(object sender, EventArgs e)
        {
            pictureBox1.Image = Hp.GetImage("user/" + Hp.user.u_no + ".png");
            label1.Text = Hp.user.u_name;
            label2.Text = "관심있는 회사 : "+Hp.user.u_sc.Split(',').Length+"개";
            label3.Text = "관심있는 소개서 : "+Hp.user.u_si.Split(',').Length+"개";
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            ((BF)FindForm()).showPage(new View.H_소개서추가());
        }

        private void pictureBox3_Click(object sender, EventArgs e)
        {
            ((BF)FindForm()).showPage(new View.I_댓글확인());
        }
    }
}
