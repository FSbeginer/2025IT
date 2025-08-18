using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025경기_1과제.Template
{
    public partial class BF : Form
    {
        public BF()
        {
            InitializeComponent();
        }

        private void BF_Load(object sender, EventArgs e)
        {

        }

        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);
            ActiveControl = null;
        }

        public void ShowPage(Form next)
        {
            Hide();
            next.ShowDialog();
            next.Dispose();
            Show();
        }

        public void ShowPage(string title)
        {
            var stack = new Stack<Form>(Application.OpenForms.Cast<Form>());
            while (stack.Count>0)
            {
                var form = stack.Pop();
                if (form.Text == title)
                {
                    break;
                }
                else
                {
                    form.Close();
                }
            }
        }
        public static void msgInfo(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "경고", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
        }
        public static Image GetImage(string url)
        {
            return Image.FromFile("./datafiles/" + url);
        }
    }
}
