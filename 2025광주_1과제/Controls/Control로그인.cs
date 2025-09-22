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
    public partial class Control로그인 : UserControl
    {
        public Control로그인()
        {
            InitializeComponent();
        }

        private void ControlLogin_Load(object sender, EventArgs e)
        {
            pictureBox1.Image = Hp.GetImage("user/" + Hp.user.u_no + ".png");
            label1.Text = Hp.user.u_name;
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            (FindForm() as Form메인).showPage(new View.Form마이페이지());
        }
    }
}
