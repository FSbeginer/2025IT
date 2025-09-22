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
    public partial class Control소개서 : UserControl
    {
        public Model.selfintroduction selfintroduction { get; set; }
        public Control소개서()
        {
            InitializeComponent();
        }

        private void Control소개서_Load(object sender, EventArgs e)
        {
            label1.Text = selfintroduction.occupation.o_name;
            label2.Text = selfintroduction.si_title;
            label3.Text = selfintroduction.si_explan;
            label4.Text = selfintroduction.user.u_situation == 1 ? "신입" : "경력";
        }

        private void label3_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            (FindForm() as View.Form소개서).showPage(new View.Form소개서내용 {selfintroduction = selfintroduction });
        }
    }
}
